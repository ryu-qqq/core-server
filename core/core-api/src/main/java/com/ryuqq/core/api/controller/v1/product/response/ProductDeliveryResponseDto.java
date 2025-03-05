package com.ryuqq.core.api.controller.v1.product.response;

import java.math.BigDecimal;

import com.ryuqq.core.domain.product.core.ProductDelivery;
import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

public record ProductDeliveryResponseDto(
	String deliveryArea,
	BigDecimal deliveryFee,
	int deliveryPeriodAverage,
	ReturnMethod returnMethodDomestic,
	ShipmentCompanyCode returnCourierDomestic,
	BigDecimal returnChargeDomestic,
	String returnExchangeAreaDomestic
) {

	public static ProductDeliveryResponseDto from(ProductDelivery productDelivery) {
		return new ProductDeliveryResponseDto(
			productDelivery.getDeliveryArea(),
			productDelivery.getDeliveryFee(),
			productDelivery.getDeliveryPeriodAverage(),
			productDelivery.getReturnMethodDomestic(),
			productDelivery.getReturnCourierDomestic(),
			productDelivery.getReturnChargeDomestic(),
			productDelivery.getReturnExchangeAreaDomestic()
		);
	}
}
