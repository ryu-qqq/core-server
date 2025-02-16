package com.ryuqq.core.external.openAi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import com.ryuqq.core.external.openAi.response.OpenAiResponse;

@Service
public class OpenAiTranslationService implements TranslationService {

	private final OpenAiClient openAiClient;
	private final OpenAiRequestResponseHandler openAiRequestResponseHandler;

	@Value("${openai.api.key}")
	private String apiKey;

	public OpenAiTranslationService(OpenAiClient openAiClient,
									OpenAiRequestResponseHandler openAiRequestResponseHandler) {
		this.openAiClient = openAiClient;
		this.openAiRequestResponseHandler = openAiRequestResponseHandler;
	}

	@Override
	public TranslateResult translate(String text) {
		return translateToEnglish(text);
	}


	public TranslateResult translateToEnglish(String productName) {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("model", "gpt-4o");  // 최신 모델 사용
		requestBody.put("messages", List.of(
			Map.of("role", "system", "content", """
        You are a professional product name translator and merchandise expert.
        Your task is to translate Korean product names into English while ensuring clarity and brand recognition.
        If the product name exceeds 60 characters in half-width format, remove unnecessary words (such as generic category words like "sports," "fishing," "hiking," "riding," "fashion") while keeping the most important product identity.
        Ensure the final translated product name does not exceed 60 characters in half-width format.
        """),
			Map.of("role", "user", "content", "Translate this product name into English considering brand and context: " + productName)
		));
		requestBody.put("max_tokens", 100);

		Map<String, Object> jsonSchema = new HashMap<>();
		jsonSchema.put("name", "product_translation_schema");
		jsonSchema.put("schema", Map.of(
			"type", "object",
			"properties", Map.of(
				"originalText", Map.of("type", "string", "description", "The original product name in Korean"),
				"translatedText", Map.of("type", "string", "description", "The translated product name in English")
			),
			"required", List.of("originalText", "translatedText"),
			"additionalProperties", false
		));

		requestBody.put("response_format", Map.of(
			"type", "json_schema",
			"json_schema", jsonSchema
		));

		ResponseEntity<OpenAiResponse> responseEntity =
			openAiClient.getTranslationResponse("Bearer " + apiKey, requestBody);

		return parseTranslationResponse(responseEntity.getBody());
	}


	private TranslateResult parseTranslationResponse(OpenAiResponse response) {
		JsonNode jsonNode = openAiRequestResponseHandler.handleResponse(response);
		return new DefaultTranslateResult(
			jsonNode.get("originalText").asText(),
			jsonNode.get("translatedText").asText()
		);

	}


}
