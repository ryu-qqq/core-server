package com.ryuqq.core.events;

public record ExternalProductGroupFailedEvent(
	long siteId,
	long productGroupId,
	Exception exception
) {
}
