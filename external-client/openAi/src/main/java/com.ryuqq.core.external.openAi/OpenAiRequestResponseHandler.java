package com.ryuqq.core.external.openAi;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.external.ExternalSiteException;
import com.ryuqq.core.external.openAi.response.OpenAiResponse;

@Component
public class OpenAiRequestResponseHandler {

	private final ObjectMapper objectMapper = new ObjectMapper();


	public JsonNode handleResponse(OpenAiResponse response) {

		if (response != null && response.choices() != null && !response.choices().isEmpty()) {
			String contentJson = response.choices().getFirst().message().content();

			try {
				return objectMapper.readTree(contentJson);
			} catch (JsonProcessingException e) {
				throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR, e);

			}
		}

		throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  "OpenAi Response null");

	}

}
