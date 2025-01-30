package com.ryuqq.core.external.buyma.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaProductInsertResponseDto(
	@JsonProperty("request_received_at")
	LocalDateTime requestReceivedAt,
	@JsonProperty("request_uid")
	String requestUid
	) {
}
