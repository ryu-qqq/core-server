package com.ryuqq.core.external.oco.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoImageInsertRequestDto(
	@JsonProperty("relativepath")
	String relativePath,
	@JsonIgnore
	int order
) {}
