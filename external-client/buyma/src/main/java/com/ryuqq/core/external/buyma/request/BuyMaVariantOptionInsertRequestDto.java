package com.ryuqq.core.external.buyma.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaVariantOptionInsertRequestDto(
	@JsonProperty("type")
	String type,
	@JsonProperty("value")
	String value
) {
}
