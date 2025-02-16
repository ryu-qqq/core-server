package com.ryuqq.core.api.controller.v1.external.request;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuyMaProductInsertFailedRequestDto(
	@JsonProperty("request_uid")
	String requestUid,

	@JsonProperty("errors")
	Map<String, List<String>> errors

) implements BuyMaEventRequestDto{}
