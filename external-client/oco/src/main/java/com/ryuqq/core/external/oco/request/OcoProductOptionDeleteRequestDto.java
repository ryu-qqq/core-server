package com.ryuqq.core.external.oco.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoProductOptionDeleteRequestDto(
	@JsonProperty("pid")
	long externalProductId,
	@JsonProperty("optionList")
	List<OcoOptionDeleteRequestDto> options
) {}
