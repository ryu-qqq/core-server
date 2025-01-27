package com.ryuqq.core.external.sellic.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SellicProductOptionInsertRequestDto(
	@JsonProperty("option_item1")  String optionItem1,
	@JsonProperty("option_item2")  String optionItem2,
	@JsonProperty("sale_status")   int saleStatus,
	@JsonProperty("add_price") BigDecimal additionalPrice,
	@JsonProperty("present_stock") int quantity

) {
}
