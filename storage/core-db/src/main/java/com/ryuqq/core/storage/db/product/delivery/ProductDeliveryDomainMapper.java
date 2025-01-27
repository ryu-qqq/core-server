package com.ryuqq.core.storage.db.product.delivery;

import com.ryuqq.core.domain.Money;
import com.ryuqq.core.domain.product.ProductDelivery;

public class ProductDeliveryDomainMapper {

	public static ProductDelivery toDomain(ProductDeliveryDto dto) {
		return ProductDelivery.create(
			dto.getProductGroupId(),
			dto.getDeliveryArea(),
			Money.wons(dto.getDeliveryFee()),
			dto.getDeliveryPeriodAverage(),
			dto.getReturnMethodDomestic(),
			dto.getReturnCourierDomestic(),
			Money.wons(dto.getReturnChargeDomestic()),
			dto.getReturnExchangeAreaDomestic()
		);
	}

}
