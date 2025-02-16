package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductDeliveryRequestDto;
import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;

@Component
class ProductDeliveryMapper implements DomainMapper<ProductDeliveryRequestDto> {

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductDeliveryRequestDto;
	}

	@Override
	public ProductGroupContextCommandBuilder map(ProductDeliveryRequestDto requestDto, ProductGroupContextCommandBuilder builder) {
		long productGroupId = builder.getProductGroupId().orElse(0L);

		ProductDeliveryCommand productDeliveryCommand = ProductDeliveryCommand.of(productGroupId, requestDto.deliveryArea(),
			requestDto.deliveryFee(), requestDto.deliveryPeriodAverage(), requestDto.returnMethodDomestic(),
			requestDto.returnCourierDomestic(), requestDto.returnChargeDomestic(), requestDto.returnExchangeAreaDomestic());

		builder.withProductDeliveryCommand(productDeliveryCommand);
		return builder;
	}

}
