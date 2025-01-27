package com.ryuqq.core.external.oco.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoOptionDeleteRequestDto(

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("product_option_id") long productOptionId,
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("pid") long pid
) {}
