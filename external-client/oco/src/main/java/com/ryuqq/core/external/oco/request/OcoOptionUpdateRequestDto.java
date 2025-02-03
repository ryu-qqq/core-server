package com.ryuqq.core.external.oco.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoOptionUpdateRequestDto(
	@JsonProperty("pid")
	long externalProductId,
	@JsonProperty("optionList")
	List<OcoOptionInsertRequestDto> options
) {

}
