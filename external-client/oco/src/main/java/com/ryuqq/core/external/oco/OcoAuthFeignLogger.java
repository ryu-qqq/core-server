package com.ryuqq.core.external.oco;

import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.AbstractFeignLogger;

public class OcoAuthFeignLogger extends AbstractFeignLogger {

	@Override
	protected String getServiceName() {
		return SiteName.OCO.name();
	}
}
