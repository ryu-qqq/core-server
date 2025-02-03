package com.ryuqq.core.external.buyma.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaVariantInsertRequestDto(
	@JsonProperty("options") List<BuyMaVariantOptionInsertRequestDto> options,
	@JsonProperty("stock_type") String stockType,
	@JsonProperty("stocks") Integer stocks
) {
}
