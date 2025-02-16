package com.ryuqq.core.external.openAi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenAiResponse(
	@JsonProperty("choices") List<Choice> choices
) {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Choice(
		@JsonProperty("message") Message message
	) {
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Message(
		@JsonProperty("content") String content
	) {
	}

}
