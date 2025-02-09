package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

public interface ProductRefundCommand {
	long productGroupId();
	ReturnMethod returnMethodDomestic();
	ShipmentCompanyCode returnCourierDomestic();
	BigDecimal returnChargeDomestic();
	String returnExchangeAreaDomestic();

}
