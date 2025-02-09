package com.ryuqq.core.storage.db.product.delivery;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductDeliveryCommand;

@Component
public class ProductDeliveryStorageMapper {

	public ProductDeliveryEntity toEntity(ProductDeliveryCommand productDeliveryCommand){
		return new ProductDeliveryEntity(
			productDeliveryCommand.productGroupId(),
			productDeliveryCommand.deliveryArea(),
			productDeliveryCommand.deliveryFee(),
			productDeliveryCommand.deliveryPeriodAverage(),
			productDeliveryCommand.returnMethodDomestic(),
			productDeliveryCommand.returnCourierDomestic(),
			productDeliveryCommand.returnChargeDomestic(),
			productDeliveryCommand.returnExchangeAreaDomestic()
		);
	}

}
