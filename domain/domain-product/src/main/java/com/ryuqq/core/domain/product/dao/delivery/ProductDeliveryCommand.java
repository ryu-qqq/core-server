package com.ryuqq.core.domain.product.dao.delivery;

import java.math.BigDecimal;

import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

 public interface ProductDeliveryCommand {
	 Long productGroupId();
	 String deliveryArea();
	 BigDecimal deliveryFee();
	 int deliveryPeriodAverage();
	 ReturnMethod returnMethodDomestic();
	 ShipmentCompanyCode returnCourierDomestic();
	 BigDecimal returnChargeDomestic();
	 String returnExchangeAreaDomestic();
}
