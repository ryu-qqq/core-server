package com.ryuqq.core.domain.product.core;

import java.math.BigDecimal;

public interface ItemDeliveryInfo {

	String getDeliveryArea();
	BigDecimal getDeliveryFee();
	int getDeliveryPeriodAverage();

}
