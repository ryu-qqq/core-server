package com.ryuqq.core.external.buyma.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.core.ExternalCategoryOptionMapping;
import com.ryuqq.core.domain.external.core.ExternalCategoryOptionQueryInterface;
import com.ryuqq.core.domain.product.core.OptionContext;
import com.ryuqq.core.domain.product.core.ProductContext;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.domain.product.core.ProductOptionContextQueryInterface;
import com.ryuqq.core.external.buyma.BuyMaOptionContext;
import com.ryuqq.core.external.buyma.helper.BuyMaSizeMatcher;
import com.ryuqq.core.external.buyma.helper.BuyMaStockCalculator;
import com.ryuqq.core.external.buyma.request.BuyMaOptionInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaVariantInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaVariantOptionInsertRequestDto;

@Component
public class BuyMaOptionMapper {

	private final ExternalCategoryOptionQueryInterface externalCategoryOptionQueryInterface;
	private final ProductOptionContextQueryInterface productOptionContextQueryInterface;

	public BuyMaOptionMapper(ExternalCategoryOptionQueryInterface externalCategoryOptionQueryInterface,
							 ProductOptionContextQueryInterface productOptionContextQueryInterface) {
		this.externalCategoryOptionQueryInterface = externalCategoryOptionQueryInterface;
		this.productOptionContextQueryInterface = productOptionContextQueryInterface;
	}

	public BuyMaOptionContext toBuyMaOptionContext(long siteId, long productGroupId, String externalCategoryId) {
		ProductOptionContext productOptionContext = productOptionContextQueryInterface.fetchByProductGroupId(
			productGroupId);

		if(externalCategoryId.isBlank()){
			return createNoOptionContext(productOptionContext.getProducts());
		}

		List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings =
			externalCategoryOptionQueryInterface.fetchBySiteIdAndExternalCategoryIds(siteId, List.of(externalCategoryId));

		return externalCategoryOptionMappings.isEmpty() ?
			createNoOptionContext(productOptionContext.getProducts()) :
			createOptionContext(externalCategoryOptionMappings, productOptionContext.getProducts());
	}

	private BuyMaOptionContext createNoOptionContext(List<? extends ProductContext> productContexts) {
		List<BuyMaOptionInsertRequestDto> options = List.of(
			new BuyMaOptionInsertRequestDto("color", "multicolor", 1, 99),
			new BuyMaOptionInsertRequestDto("size", "FREE", 1, 0)
		);

		List<BuyMaVariantInsertRequestDto> variants = List.of(
			new BuyMaVariantInsertRequestDto(
				List.of(
					new BuyMaVariantOptionInsertRequestDto("color", "multicolor"),
					new BuyMaVariantOptionInsertRequestDto("size", "FREE")
				),
				BuyMaStockCalculator.determineStockType(productContexts),
				BuyMaStockCalculator.calculateTotalQuantity(productContexts)
			)
		);

		return new BuyMaOptionContext(options, variants, BuyMaStockCalculator.getOptionComment(productContexts));
	}

	private BuyMaOptionContext createOptionContext(List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings,
												   List<? extends ProductContext> productContexts) {
		List<BuyMaOptionInsertRequestDto> options = new ArrayList<>();
		options.add(new BuyMaOptionInsertRequestDto("color", "MultiColor", 1, 99));

		List<BuyMaVariantInsertRequestDto> variants = new ArrayList<>();
		populateOptionsWithProductSizes(options, externalCategoryOptionMappings, productContexts);
		populateVariantsWithStock(variants, productContexts, externalCategoryOptionMappings);

		return variants.isEmpty() ? createNoOptionContext(productContexts) :
			new BuyMaOptionContext(options, variants, BuyMaStockCalculator.getOptionComment(productContexts));
	}

	private void populateOptionsWithProductSizes(List<BuyMaOptionInsertRequestDto> options,
												 List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings,
												 List<? extends ProductContext> productContexts) {
		int position = 1;
		for (ProductContext productContext : productContexts) {
			for (OptionContext optionContext : productContext.getOptions()) {
				if (optionContext.getOptionName().isSize()) {
					long masterId = BuyMaSizeMatcher.findMatchingOptionId(optionContext.getOptionValue(), externalCategoryOptionMappings);
					if (masterId != 0L) {
						options.add(new BuyMaOptionInsertRequestDto("size", optionContext.getOptionValue(), position++, masterId));
					}
				}
			}
		}
	}

	private void populateVariantsWithStock(List<BuyMaVariantInsertRequestDto> variants,
										   List<? extends ProductContext> productContexts,
										   List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings) {
		Map<Long, Integer> masterIdToStock = new HashMap<>();
		Map<Long, String> masterIdToOptionValue = new HashMap<>();

		for (ProductContext productContext : productContexts) {
			for (OptionContext optionContext : productContext.getOptions()) {
				if (optionContext.getOptionName().isSize()) {
					long masterId = BuyMaSizeMatcher.findMatchingOptionId(optionContext.getOptionValue(), externalCategoryOptionMappings);
					if (masterId != 0L) {
						masterIdToStock.merge(masterId, productContext.getProduct().getQuantity(), Integer::sum);
						masterIdToOptionValue.putIfAbsent(masterId, optionContext.getOptionValue());
					}
				}
			}
		}

		masterIdToStock.forEach((masterId, totalStock) -> {
			String optionValue = masterIdToOptionValue.get(masterId);
			variants.add(new BuyMaVariantInsertRequestDto(
				List.of(
					new BuyMaVariantOptionInsertRequestDto("color", "MultiColor"),
					new BuyMaVariantOptionInsertRequestDto("size", optionValue)
				),
				BuyMaStockCalculator.determineStockType(totalStock),
				Math.min(totalStock, 50)
			));
		});
	}
}
