package com.ryuqq.core.external.buyma.mapper;


import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestWrapperDto;

@Component
public class BuyMaProductMapper {

	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;

	public BuyMaProductMapper(ProductGroupContextQueryInterface productGroupContextQueryInterface) {
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
	}

	public BuyMaProductInsertRequestWrapperDto toInsetRequestDto(ExternalProductGroup externalProductGroup){


		// return BuyMaProductInsertFactory.createInsertRequestDto(
		// 	externalProductGroup,
		//
		//
		//
		// );

		return null;
	}


}
