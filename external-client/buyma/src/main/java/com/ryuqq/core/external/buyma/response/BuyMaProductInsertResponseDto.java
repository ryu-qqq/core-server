package com.ryuqq.core.external.buyma.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuyMaProductInsertResponseDto(
	@JsonProperty("request_received_at")
	String requestReceivedAt,
	@JsonProperty("request_uid")
	String requestUid
	) {
}
