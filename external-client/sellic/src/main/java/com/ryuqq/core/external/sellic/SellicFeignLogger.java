package com.ryuqq.core.external.sellic;

import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.AbstractFeignLogger;

public class SellicFeignLogger extends AbstractFeignLogger {

	@Override
	protected String getServiceName() {
		return SiteName.SELLIC.name();
	}
}
