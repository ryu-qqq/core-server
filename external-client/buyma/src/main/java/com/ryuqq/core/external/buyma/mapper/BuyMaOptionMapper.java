package com.ryuqq.core.external.buyma.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.core.ExternalCategoryOptionMapping;
import com.ryuqq.core.domain.external.core.ExternalCategoryOptionQueryInterface;
import com.ryuqq.core.domain.product.core.ProductContextInterface;
import com.ryuqq.core.domain.product.core.Sku;
import com.ryuqq.core.domain.product.core.Variant;
import com.ryuqq.core.external.buyma.BuyMaOptionContext;
import com.ryuqq.core.external.buyma.helper.BuyMaSizeMatcher;
import com.ryuqq.core.external.buyma.helper.BuyMaStockCalculator;
import com.ryuqq.core.external.buyma.request.BuyMaOptionInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaVariantInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaVariantOptionInsertRequestDto;

@Component
public class BuyMaOptionMapper {

	private final ExternalCategoryOptionQueryInterface externalCategoryOptionQueryInterface;
	private final ProductContextInterface productContextInterface;

	public BuyMaOptionMapper(ExternalCategoryOptionQueryInterface externalCategoryOptionQueryInterface,
							 ProductContextInterface productContextInterface) {
		this.externalCategoryOptionQueryInterface = externalCategoryOptionQueryInterface;
		this.productContextInterface = productContextInterface;
	}

	public BuyMaOptionContext toBuyMaOptionContext(long siteId, long productGroupId, String externalCategoryId) {
		List<? extends Sku> skus = productContextInterface.fetchByProductGroupId(productGroupId);
		List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings =
			externalCategoryOptionQueryInterface.fetchBySiteIdAndExternalCategoryIds(siteId, List.of(externalCategoryId));

		return externalCategoryOptionMappings.isEmpty() ?
			createNoOptionContext(skus) :
			createOptionContext(externalCategoryOptionMappings, skus);
	}

	private BuyMaOptionContext createNoOptionContext(List<? extends Sku> skus) {
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
				BuyMaStockCalculator.determineStockType(skus),
				BuyMaStockCalculator.calculateTotalQuantity(skus)
			)
		);

		return new BuyMaOptionContext(options, variants, BuyMaStockCalculator.getOptionComment(skus));
	}

	private BuyMaOptionContext createOptionContext(List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings,
												   List<? extends Sku> skus) {
		List<BuyMaOptionInsertRequestDto> options = new ArrayList<>();
		options.add(new BuyMaOptionInsertRequestDto("color", "MultiColor", 1, 99));

		List<BuyMaVariantInsertRequestDto> variants = new ArrayList<>();
		populateOptionsWithProductSizes(options, externalCategoryOptionMappings, skus);
		populateVariantsWithStock(variants, skus, externalCategoryOptionMappings);

		return variants.isEmpty() ? createNoOptionContext(skus) :
			new BuyMaOptionContext(options, variants, BuyMaStockCalculator.getOptionComment(skus));
	}

	private void populateOptionsWithProductSizes(List<BuyMaOptionInsertRequestDto> options,
												 List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings,
												 List<? extends Sku> skus) {
		int position = 1;
		for (Sku sku : skus) {
			for (Variant variant : sku.getVariants()) {
				if (variant.getOptionName().isSize()) {
					long masterId = BuyMaSizeMatcher.findMatchingOptionId(variant.getOptionValue(), externalCategoryOptionMappings);
					if (masterId != 0L) {
						options.add(new BuyMaOptionInsertRequestDto("size", variant.getOptionValue(), position++, masterId));
					}
				}
			}
		}
	}

	private void populateVariantsWithStock(List<BuyMaVariantInsertRequestDto> variants,
										   List<? extends Sku> skus,
										   List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings) {
		Map<Long, Integer> masterIdToStock = new HashMap<>();
		Map<Long, String> masterIdToOptionValue = new HashMap<>();

		for (Sku sku : skus) {
			for (Variant variant : sku.getVariants()) {
				if (variant.getOptionName().isSize()) {
					long masterId = BuyMaSizeMatcher.findMatchingOptionId(variant.getOptionValue(), externalCategoryOptionMappings);
					if (masterId != 0L) {
						masterIdToStock.merge(masterId, sku.getQuantity(), Integer::sum);
						masterIdToOptionValue.putIfAbsent(masterId, variant.getOptionValue());
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
