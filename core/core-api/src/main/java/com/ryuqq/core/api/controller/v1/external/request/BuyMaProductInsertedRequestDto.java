package com.ryuqq.core.api.controller.v1.external.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuyMaProductInsertedRequestDto(
	long id,
	@JsonProperty("reference_number")
	String referenceNumber
) implements BuyMaEventRequestDto {
}
