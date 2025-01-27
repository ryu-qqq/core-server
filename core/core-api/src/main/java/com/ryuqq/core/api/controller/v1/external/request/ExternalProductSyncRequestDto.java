package com.ryuqq.core.api.controller.v1.external.request;

import com.ryuqq.core.enums.SyncStatus;

public record ExternalProductSyncRequestDto(
	long siteId,
	SyncStatus status
) {
}
