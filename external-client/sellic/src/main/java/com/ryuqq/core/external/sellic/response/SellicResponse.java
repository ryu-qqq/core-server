package com.ryuqq.core.external.sellic.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SellicResponse(
	String result,
	String message,
	@JsonProperty("product_id")
	String productId
) {}
