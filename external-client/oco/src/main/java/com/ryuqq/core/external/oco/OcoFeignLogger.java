package com.ryuqq.core.external.oco;

import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.AbstractFeignLogger;

public class OcoFeignLogger extends AbstractFeignLogger {

	@Override
	protected String getServiceName() {
		return SiteName.OCO.name();
	}
}
