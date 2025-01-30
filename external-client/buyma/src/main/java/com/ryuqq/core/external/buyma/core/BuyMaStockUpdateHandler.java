package com.ryuqq.core.external.buyma.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponse;

@Component
public class BuyMaStockUpdateHandler implements UpdateTypeHandler {


	@Override
	public boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType) {
		return SiteName.BUYMA.equals(siteName) && ProductDomainEventType.STOCK.equals(productDomainEventType);
	}

	@Override
	public BuyMaProductInsertResponse handle(ExternalProductGroup externalProductGroup) {

		return null;

	}
}
