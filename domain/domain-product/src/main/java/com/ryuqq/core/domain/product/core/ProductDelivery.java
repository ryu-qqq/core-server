package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

public interface ProductDelivery {
	Long getProductGroupId();
	String getDeliveryArea();
	BigDecimal getDeliveryFee();
	int getDeliveryPeriodAverage();
	ReturnMethod getReturnMethodDomestic();
	ShipmentCompanyCode getReturnCourierDomestic();
	BigDecimal getReturnChargeDomestic();
	String getReturnExchangeAreaDomestic();
}
