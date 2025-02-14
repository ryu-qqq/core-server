package com.ryuqq.core.storage.db.external;

import com.ryuqq.core.domain.external.dao.history.ExternalProductSyncCommand;

public class ExternalProductSyncRequestStorageMapper {

	public static ExternalProductSyncRequestEntity toEntity(ExternalProductSyncCommand externalProductSyncCommand){
		return new ExternalProductSyncRequestEntity(
			externalProductSyncCommand.traceId(),
			externalProductSyncCommand.siteId(),
			externalProductSyncCommand.productGroupId(),
			externalProductSyncCommand.externalProductGroupId(),
			externalProductSyncCommand.syncResult()
		);
	}


}
