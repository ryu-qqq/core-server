package com.ryuqq.core.external.oco.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductContext;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.domain.product.core.ProductOptionContextQueryInterface;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.request.OcoOptionInsertRequestDto;

@Component
public class OcoOptionConverter {

	private final ProductOptionContextQueryInterface productOptionContextQueryInterface;
	private final OcoOptionMatcher ocoOptionMatcher;

	public OcoOptionConverter(ProductOptionContextQueryInterface productOptionContextQueryInterface,
							  OcoOptionMatcher ocoOptionMatcher) {
		this.productOptionContextQueryInterface = productOptionContextQueryInterface;
		this.ocoOptionMatcher = ocoOptionMatcher;
	}

	public List<OcoOptionContext> generateOptionContext(long productGroupId, String externalProductGroupId){
		ProductOptionContext productOptionContext = fetchProductOptionContext(productGroupId);
		OptionType optionType = productOptionContext.getOptionType();
		List<OcoOptionContext> ocoOptionContexts = toOcoOptionInsertRequestDto(optionType, productOptionContext.getProducts());

		if(externalProductGroupId != null){
			return ocoOptionMatcher.checkUpdates(ocoOptionContexts, externalProductGroupId);
		}

		return ocoOptionContexts;
	}

	private ProductOptionContext fetchProductOptionContext(long productGroupId) {
		return productOptionContextQueryInterface.fetchByProductGroupId(productGroupId);
	}


	private List<OcoOptionContext> toOcoOptionInsertRequestDto(OptionType optionType, List<? extends ProductContext> productContexts){
		return productContexts.stream()
			.map(s -> {
				String optionData1 ="";
				String optionData2 = "";

				if (optionType.isOneDepthOption()) {
					optionData1 = s.getOptions().getFirst().getOptionValue();
				}

				if (optionType.isTwoDepthOption()) {
					optionData2 = s.getOptions().getLast().getOptionValue();
				}

				OcoOptionInsertRequestDto optionInsertRequestDto = new OcoOptionInsertRequestDto.Builder()
					.optionData1(optionData1)
					.optionData2(optionData2)
					.quantity(s.getProduct().getQuantity())
					.optionUse("Y")
					.additionalPrice(s.getProduct().getAdditionalPrice().intValue())
					.build();

				return new OcoOptionContext(s.getProduct().getId(), optionInsertRequestDto, false);

			})
			.toList();
	}


}
