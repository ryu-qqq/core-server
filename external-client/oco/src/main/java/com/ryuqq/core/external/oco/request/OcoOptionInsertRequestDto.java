package com.ryuqq.core.external.oco.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoOptionInsertRequestDto(
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("product_option_id") Integer productOptionId,
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("pid") Integer pid,
	@JsonProperty("option_data1") String optionData1,
	@JsonProperty("option_data2") String optionData2,
	@JsonProperty("option_product_count") int quantity,
	@JsonProperty("option_use_yn") String optionUse,
	@JsonProperty("option_price") int additionalPrice
) {

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Integer productOptionId;
		private Integer pid;
		private String optionData1;
		private String optionData2;
		private int quantity;
		private String optionUse;
		private int additionalPrice;

		public Builder productOptionId(Integer productOptionId) {
			this.productOptionId = productOptionId;
			return this;
		}

		public Builder pid(Integer pid) {
			this.pid = pid;
			return this;
		}

		public Builder optionData1(String optionData1) {
			this.optionData1 = optionData1;
			return this;
		}

		public Builder optionData2(String optionData2) {
			this.optionData2 = optionData2;
			return this;
		}

		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder optionUse(String optionUse) {
			this.optionUse = optionUse;
			return this;
		}

		public Builder additionalPrice(int additionalPrice) {
			this.additionalPrice = additionalPrice;
			return this;
		}

		public OcoOptionInsertRequestDto build() {
			return new OcoOptionInsertRequestDto(productOptionId, pid, optionData1, optionData2, quantity, optionUse, additionalPrice);
		}
	}

}
