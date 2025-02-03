package com.ryuqq.core.external.oco.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoProductPriceUpdateRequestDto(
	@JsonProperty("pid")
	long externalProductId,
	PriceProduct product
) {
	public record PriceProduct(
		@JsonProperty("pid")
		long externalProductId,
		@JsonProperty("price")
		long regularPrice,
		@JsonProperty("originprice")
		long currentPrice
	){

	}
}
