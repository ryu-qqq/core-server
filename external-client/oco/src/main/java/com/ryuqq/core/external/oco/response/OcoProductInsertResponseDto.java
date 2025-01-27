package com.ryuqq.core.external.oco.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoProductInsertResponseDto(
	int pid,
	@JsonProperty("oco_code")
	String ocoCode,
	List<OptionList> optionList
) {
	public record OptionList(
		@JsonProperty("product_option_id")
		int productOptionId,
		@JsonProperty("option_data1")
		String optionData1,
		@JsonProperty("option_data2")
		String optionData2
	){}
}
