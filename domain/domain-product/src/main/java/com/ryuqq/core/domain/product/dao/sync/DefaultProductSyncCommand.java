package com.ryuqq.core.domain.product.dao.sync;

import com.ryuqq.core.enums.SyncStatus;

public record DefaultProductSyncCommand(
	long productGroupId,
	SyncStatus status
) implements ProductSyncCommand {

}
