package com.ryuqq.core.external.oco.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductContextInterface;
import com.ryuqq.core.domain.product.core.Sku;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.request.OcoOptionInsertRequestDto;

@Component
public class OcoOptionConverter {

	private final ProductContextInterface productContextInterface;
	private final OcoOptionMatcher ocoOptionMatcher;

	public OcoOptionConverter(ProductContextInterface productContextInterface, OcoOptionMatcher ocoOptionMatcher) {
		this.productContextInterface = productContextInterface;
		this.ocoOptionMatcher = ocoOptionMatcher;
	}

	public List<OcoOptionContext> generateOptionContext(long productGroupId, String externalProductGroupId){
		List<? extends Sku> skus = fetchSkus(productGroupId);
		OptionType optionType = determineOptionType(skus);
		List<OcoOptionContext> ocoOptionContexts = toOcoOptionInsertRequestDto(optionType, skus);

		if(externalProductGroupId != null){
			return ocoOptionMatcher.checkUpdates(ocoOptionContexts, externalProductGroupId);
		}

		return ocoOptionContexts;
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

	private List<OcoOptionContext> toOcoOptionInsertRequestDto(OptionType optionType, List<? extends Sku> skus){
		return skus.stream()
			.map(s -> {
				String optionData1 ="";
				String optionData2 = "";

				if (optionType.isOneDepthOption()) {
					optionData1 = s.getVariants().getFirst().getOptionValue();
				}

				if (optionType.isTwoDepthOption()) {
					optionData2 = s.getVariants().getLast().getOptionValue();
				}

				OcoOptionInsertRequestDto optionInsertRequestDto = new OcoOptionInsertRequestDto.Builder()
					.optionData1(optionData1)
					.optionData2(optionData2)
					.quantity(s.getQuantity())
					.optionUse("Y")
					.additionalPrice(s.getAdditionalPrice().intValue())
					.build();

				return new OcoOptionContext(s.getId(), optionInsertRequestDto, false);

			})
			.toList();
	}


}
