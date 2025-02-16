package com.ryuqq.core.external.openAi;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ryuqq.core.external.openAi.response.OpenAiResponse;

@FeignClient(name = "openAiClient", url = "https://api.openai.com/v1")
public interface OpenAiClient {

	@PostMapping(value = "/chat/completions", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<OpenAiResponse> getTranslationResponse(
		@RequestHeader("Authorization") String authorization,
		@RequestBody Map<String, Object> requestBody
	);

}
