package com.ryuqq.core.api.controller.v1.external.request;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public record BuyMaProductInsertFailedRequestDto(
	@JsonProperty("request_uid")
	String requestUid,
    Map<String, Map<String, ErrorDetail>> errors

) implements BuyMaEventRequestDto{

	public record ErrorDetail(
		@JsonProperty("memo")
		List<String> memo
	) {}
}
