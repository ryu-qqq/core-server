package com.ryuqq.core.external.buyma.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaOptionInsertRequestDto(
	@JsonProperty("type")
	String type,
	@JsonProperty("value")
	String value,
	@JsonProperty("position")
	int position,
	@JsonProperty("master_id")
	long masterId
) {
}
