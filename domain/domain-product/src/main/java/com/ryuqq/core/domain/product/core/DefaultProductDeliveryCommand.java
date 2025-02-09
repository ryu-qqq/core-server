package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

record DefaultProductDeliveryCommand(long productGroupId, String deliveryArea, BigDecimal deliveryFee,
											int deliveryPeriodAverage, ReturnMethod returnMethodDomestic,
											ShipmentCompanyCode returnCourierDomestic, BigDecimal returnChargeDomestic,
											String returnExchangeAreaDomestic)
	implements ProductDeliveryCommand {

	@Override
	public DefaultProductDeliveryCommand assignProductGroupId(long productGroupId) {
		return new DefaultProductDeliveryCommand(productGroupId, deliveryArea, deliveryFee,
			deliveryPeriodAverage, returnMethodDomestic, returnCourierDomestic,
			returnChargeDomestic, returnExchangeAreaDomestic);
	}


}
