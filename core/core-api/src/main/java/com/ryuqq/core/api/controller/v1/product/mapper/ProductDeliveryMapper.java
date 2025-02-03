package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductDeliveryRequestDto;
import com.ryuqq.core.domain.Money;
import com.ryuqq.core.domain.product.ProductDelivery;
import com.ryuqq.core.domain.product.ProductGroupContext;

@Component
public class ProductDeliveryMapper implements DomainMapper<ProductDeliveryRequestDto> {

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductDeliveryRequestDto;
	}

	@Override
	public ProductGroupContext.Builder map(ProductDeliveryRequestDto source, ProductGroupContext.Builder builder) {
		ProductDelivery productDelivery = ProductDelivery.create(
			null,
			source.deliveryArea(),
			Money.wons(source.deliveryFee()),
			source.deliveryPeriodAverage(),
			source.returnMethodDomestic(),
			source.returnCourierDomestic(),
			Money.wons(source.returnChargeDomestic()),
			source.returnExchangeAreaDomestic()
		);

		builder.productDelivery(productDelivery);
		return builder;
	}
}
