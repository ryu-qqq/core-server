package com.ryuqq.core.external.buyma.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaImageInsertRequestDto(
	@JsonProperty("path")
	String path,
	@JsonProperty("position")
	int position
) {
}
