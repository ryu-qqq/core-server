package com.ryuqq.core.domain.product.dao.delivery;

import com.ryuqq.core.domain.product.ProductDelivery;

public class ProductDeliveryCommandFactory {

	public static ProductDeliveryCommand createCommandFrom(ProductDelivery delivery) {
		return new DefaultProductDeliveryCommand(
			delivery.getProductGroupId(),
			delivery.getDeliveryArea(),
			delivery.getDeliveryFee(),
			delivery.getDeliveryPeriodAverage(),
			delivery.getReturnMethodDomestic(),
			delivery.getReturnCourierDomestic(),
			delivery.getReturnChargeDomestic(),
			delivery.getReturnExchangeAreaDomestic()
		);
	}

}
