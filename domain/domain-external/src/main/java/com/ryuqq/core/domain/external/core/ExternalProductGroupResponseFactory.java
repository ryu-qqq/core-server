package com.ryuqq.core.domain.external.core;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SyncStatus;

public class ExternalProductGroupResponseFactory {

	public static ExternalProductGroup mapResponse(ProductDomainEventType eventType,
												   ExternalProductGroup productGroup,
												   ExternalMallProductGroupRequestResponse response) {
		return switch (eventType) {
			case PRICE -> mapForPrice(productGroup, response);
			case STOCK -> mapForStock(productGroup, response);
			default -> mapForProductGroupSync(productGroup, response);
		};
	}

	public static ExternalProductGroup failStatus(ExternalProductGroup productGroup) {
		return new ExternalProductGroup.Builder(productGroup)
			.status(SyncStatus.FAILED)
			.build();
	}

	private static ExternalProductGroup mapForProductGroupSync(ExternalProductGroup productGroup,
															ExternalMallProductGroupRequestResponse response) {
		return new ExternalProductGroup.Builder(productGroup)
			.status(response.getSyncStatus())
			.externalProductGroupId(response.getExternalProductGroupId())
			.currentPrice(response.getCurrentPrice())
			.regularPrice(response.getRegularPrice())
			.fixedPrice(response.fixedPrice())
			.productName(response.getProductGroupName())
			.build();
	}


	private static ExternalProductGroup mapForPrice(ExternalProductGroup productGroup,
											 ExternalMallProductGroupRequestResponse response) {
		return new ExternalProductGroup.Builder(productGroup)
			.status(response.getSyncStatus())
			.currentPrice(response.getCurrentPrice())
			.regularPrice(response.getRegularPrice())
			.fixedPrice(response.fixedPrice())
			.build();
	}

	private static ExternalProductGroup mapForStock(ExternalProductGroup productGroup,
											 ExternalMallProductGroupRequestResponse response) {
		return new ExternalProductGroup.Builder(productGroup)
			.status(response.getSyncStatus())
			.build();
	}



}
