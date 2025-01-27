package com.ryuqq.core.domain.external.core;

import java.math.BigDecimal;

public interface ExternalProductRequestResponse {
	String getExternalProductId();
	String getOptionName();
	String getOptionValue();
	int getQuantity();
	BigDecimal additionalPrice();
	boolean isSoldOut();
	boolean isDisplayed();
}
