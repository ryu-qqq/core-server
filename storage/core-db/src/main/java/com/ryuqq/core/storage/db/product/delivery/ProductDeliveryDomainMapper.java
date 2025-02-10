package com.ryuqq.core.storage.db.product.delivery;

import com.ryuqq.core.domain.Money;
import com.ryuqq.core.domain.product.core.DefaultProductDelivery;

public class ProductDeliveryDomainMapper {

	public static DefaultProductDelivery toDomain(ProductDeliveryDto dto) {
		return DefaultProductDelivery.create(
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
