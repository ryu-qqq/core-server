package com.ryuqq.core.storage.db.external.dto;

import java.util.List;

import com.ryuqq.core.enums.SyncStatus;

public record ExternalProductGroupStorageFilterDto(
	List<Long> productGroupIds,
	SyncStatus status
) {
}
