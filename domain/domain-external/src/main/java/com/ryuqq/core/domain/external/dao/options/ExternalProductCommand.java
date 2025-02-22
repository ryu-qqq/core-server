package com.ryuqq.core.domain.external.dao.options;

import java.math.BigDecimal;

public interface ExternalProductCommand {
	String externalProductGroupId();
	String externalProductId();
	long productId();
	String optionValue();
	int quantity();
	BigDecimal additionalPrice();
	boolean soldOut();
	boolean displayed();

}
