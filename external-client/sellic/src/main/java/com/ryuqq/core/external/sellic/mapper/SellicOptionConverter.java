package com.ryuqq.core.external.sellic.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductContext;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.domain.product.core.ProductOptionContextQueryInterface;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.external.sellic.SellicOptionContext;
import com.ryuqq.core.external.sellic.request.SellicProductOptionInsertRequestDto;

@Component
public class SellicOptionConverter {

	private final ProductOptionContextQueryInterface productOptionContextQueryInterface;

	public SellicOptionConverter(ProductOptionContextQueryInterface productOptionContextQueryInterface) {
		this.productOptionContextQueryInterface = productOptionContextQueryInterface;
	}

	public SellicOptionContext generateOptionContext(long productGroupId) {
		ProductOptionContext productOptionContext = fetchProductOptionContext(productGroupId);
		OptionType optionType = productOptionContext.getOptionType();

		String optionName1 = resolveOptionName(optionType, productOptionContext.getProducts(), true);
		String optionName2 = resolveOptionName(optionType, productOptionContext.getProducts(), false);

		List<SellicProductOptionInsertRequestDto> sellicOptionDtos = createOptionDtos(optionType, productOptionContext.getProducts());

		return new SellicOptionContext(sellicOptionDtos, optionName1, optionName2);
	}

	private ProductOptionContext fetchProductOptionContext(long productGroupId) {
		return productOptionContextQueryInterface.fetchByProductGroupId(productGroupId);
	}




	private String resolveOptionName(OptionType optionType, List<? extends ProductContext> productContexts, boolean isFirstOption) {
		if (optionType.isSingleOption()) {
			if(isFirstOption) return "단품";
			return "";
		}

		return productContexts.stream()
			.findFirst()
			.flatMap(sku -> getOptionNameFromSku(sku, isFirstOption))
			.orElse("");
	}

	private Optional<String> getOptionNameFromSku(ProductContext productContext, boolean isFirstOption) {
		if (productContext.getOptions().isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(
			isFirstOption ? productContext.getOptions().getFirst().getOptionName().name() :
				(productContext.getOptions().size() > 1 ? productContext.getOptions().getLast().getOptionName().name() : "")
		);
	}

	private List<SellicProductOptionInsertRequestDto> createOptionDtos(OptionType optionType, List<? extends ProductContext> skus) {
		return skus.stream()
			.map(sku -> toSellicOptionDto(optionType, sku))
			.toList();
	}

	private SellicProductOptionInsertRequestDto toSellicOptionDto(OptionType optionType, ProductContext productContext) {
		String optionData1 = "단품";
		String optionData2 = "";

		if (optionType.isMultiOption()) {
			optionData1 = productContext.getOptions().getFirst().getOptionValue();

			if (optionType.isTwoDepthOption() && productContext.getOptions().size() > 1) {
				optionData2 = productContext.getOptions().getLast().getOptionValue();
			}
		}

		return new SellicProductOptionInsertRequestDto(
			optionData1,
			optionData2,
			productContext.getProduct().isDisplayed() ? 0 : 1,
			productContext.getProduct().getAdditionalPrice(),
			productContext.getProduct().getQuantity()
		);
	}
}
