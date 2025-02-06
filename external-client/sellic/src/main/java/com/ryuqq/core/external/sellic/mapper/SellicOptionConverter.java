package com.ryuqq.core.external.sellic.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductContextInterface;
import com.ryuqq.core.domain.product.core.Sku;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.external.sellic.SellicOptionContext;
import com.ryuqq.core.external.sellic.request.SellicProductOptionInsertRequestDto;

@Component
public class SellicOptionConverter {

	private final ProductContextInterface productContextInterface;

	public SellicOptionConverter(ProductContextInterface productContextInterface) {
		this.productContextInterface = productContextInterface;
	}

	public SellicOptionContext generateOptionContext(long productGroupId) {
		List<? extends Sku> skus = fetchSkus(productGroupId);
		OptionType optionType = determineOptionType(skus);

		String optionName1 = resolveOptionName(optionType, skus, true);
		String optionName2 = resolveOptionName(optionType, skus, false);

		List<SellicProductOptionInsertRequestDto> sellicOptionDtos = createOptionDtos(optionType, skus);

		return new SellicOptionContext(sellicOptionDtos, optionName1, optionName2);
	}

	private List<? extends Sku> fetchSkus(long productGroupId) {
		return Optional.ofNullable(productContextInterface.fetchByProductGroupId(productGroupId))
			.orElse(Collections.emptyList());
	}

	private OptionType determineOptionType(List<? extends Sku> skus) {
		if (skus.isEmpty()) {
			return OptionType.SINGLE;
		}

		int optionSize = skus.getFirst().getVariants().size();
		return switch (optionSize) {
			case 0 -> OptionType.SINGLE;
			case 1 -> OptionType.OPTION_ONE;
			default -> OptionType.OPTION_TWO;
		};
	}

	private String resolveOptionName(OptionType optionType, List<? extends Sku> skus, boolean isFirstOption) {
		if (optionType.isSingleOption()) {
			if(isFirstOption) return "단품";
			return "";
		}

		return skus.stream()
			.findFirst()
			.flatMap(sku -> getOptionNameFromSku(sku, isFirstOption))
			.orElse("");
	}

	private Optional<String> getOptionNameFromSku(Sku sku, boolean isFirstOption) {
		if (sku.getVariants().isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(
			isFirstOption ? sku.getVariants().getFirst().getOptionName().name() :
				(sku.getVariants().size() > 1 ? sku.getVariants().getLast().getOptionName().name() : "")
		);
	}

	private List<SellicProductOptionInsertRequestDto> createOptionDtos(OptionType optionType, List<? extends Sku> skus) {
		return skus.stream()
			.map(sku -> toSellicOptionDto(optionType, sku))
			.toList();
	}

	private SellicProductOptionInsertRequestDto toSellicOptionDto(OptionType optionType, Sku sku) {
		String optionData1 = "단품";
		String optionData2 = "";

		if (optionType.isMultiOption()) {
			optionData1 = sku.getVariants().get(0).getOptionValue();

			if (optionType.isTwoDepthOption() && sku.getVariants().size() > 1) {
				optionData2 = sku.getVariants().get(1).getOptionValue();
			}
		}

		return new SellicProductOptionInsertRequestDto(
			optionData1,
			optionData2,
			sku.isDisplayed() ? 0 : 1,
			sku.getAdditionalPrice(),
			sku.getQuantity()
		);
	}
}
