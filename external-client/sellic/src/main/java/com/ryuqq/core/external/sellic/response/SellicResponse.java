package com.ryuqq.core.external.sellic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SellicResponse(
	String result,
	String message,
	@JsonProperty("product_id")
	String productId,
	@JsonProperty("datas")
	Object datas
) {}
