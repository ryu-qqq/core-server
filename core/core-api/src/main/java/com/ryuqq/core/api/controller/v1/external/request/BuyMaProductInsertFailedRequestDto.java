package com.ryuqq.core.api.controller.v1.external.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuyMaProductInsertFailedRequestDto(
	@JsonProperty("request_uid")
	String requestUid,

	@JsonProperty("errors")
	Object errors

) implements BuyMaEventRequestDto{}
