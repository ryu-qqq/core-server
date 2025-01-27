package com.ryuqq.core.domain.product.dao.sync;

import com.ryuqq.core.enums.SyncStatus;

public interface ProductSyncCommand {
	long productGroupId();
	SyncStatus status();
}
