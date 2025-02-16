package com.ryuqq.core.domain.product.dao.delivery;

import java.math.BigDecimal;

import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

public interface ProductDeliveryCommand {
	long productGroupId();
	String deliveryArea();
	BigDecimal deliveryFee();
	int deliveryPeriodAverage();
	ReturnMethod returnMethodDomestic();
	ShipmentCompanyCode returnCourierDomestic();
	BigDecimal returnChargeDomestic();
	String returnExchangeAreaDomestic();

	static ProductDeliveryCommand of(long productGroupId, String deliveryArea, BigDecimal deliveryFee,
									 int deliveryPeriodAverage, ReturnMethod returnMethodDomestic,
									 ShipmentCompanyCode returnCourierDomestic, BigDecimal returnChargeDomestic,
									 String returnExchangeAreaDomestic){

		return new DefaultProductDeliveryCommand(productGroupId, deliveryArea, deliveryFee,
			deliveryPeriodAverage, returnMethodDomestic, returnCourierDomestic,
			returnChargeDomestic, returnExchangeAreaDomestic);
	}


	ProductDeliveryCommand assignProductGroupId(long productGroupId);


}
