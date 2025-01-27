package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

public interface ItemRefundInfo {

	ReturnMethod getReturnMethodDomestic();
	ShipmentCompanyCode getReturnCourierDomestic();
	BigDecimal getReturnChargeDomestic();
	String getReturnExchangeAreaDomestic();
}
