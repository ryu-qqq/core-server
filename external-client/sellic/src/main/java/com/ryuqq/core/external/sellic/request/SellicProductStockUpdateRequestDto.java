package com.ryuqq.core.external.sellic.request;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SellicProductStockUpdateRequestDto(
	@JsonProperty("product_id")  long externalProductGroupId,
	@JsonProperty("option_name1")  String optionName1,
	@JsonProperty("option_name2")  String optionName2,
	@JsonProperty("product_stocks") List<ProductStock> options
) {
	public record ProductStock(
		@JsonProperty("option_item1")  String optionItem1,
		@JsonProperty("option_item2")  String optionItem2,
		@JsonProperty("add_price") BigDecimal additionalPrice,
		@JsonProperty("present_stock") int quantity

	){}

}
