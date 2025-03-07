package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.Origin;

public interface ProductNotice {

	Long getProductGroupId();
	String getMaterial();
	String getColor();
	String getSize();
	String getMaker();
	Origin getOrigin();
	String getWashingMethod();
	String getYearMonth();
	String getAssuranceStandard();
	String getAsPhone();

}
