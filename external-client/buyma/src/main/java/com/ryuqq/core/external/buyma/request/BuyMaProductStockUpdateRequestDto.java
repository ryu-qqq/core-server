package com.ryuqq.core.external.buyma.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaProductStockUpdateRequestDto(
	@JsonProperty("reference_number") String referenceNumber,
	@JsonProperty("variants") List<BuyMaVariantInsertRequestDto> variants
) {}
