package com.ryuqq.core.domain.external.core;

import java.util.List;

import com.ryuqq.core.enums.SiteName;

public interface ExternalProductGroupRequestResponse {
	long getSiteId();
	SiteName getSiteName();
	long getProductGroupId();
	String getExternalProductGroupId();
	String getProductGroupName();
	boolean isSoldOut();
	boolean isDisplayed();
	List<? extends ExternalProductRequestResponse> getExternalProductRequestResponses();
}
