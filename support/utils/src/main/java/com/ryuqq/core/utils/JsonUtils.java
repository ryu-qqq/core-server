package com.ryuqq.core.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {

	private static final ObjectMapper objectMapper = createObjectMapper();

	private static ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new com.fasterxml.jackson.datatype.jdk8.Jdk8Module()); // Optional 지원 추가
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
		objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		return objectMapper;
	}


	public static <T> String toJson(T valueType){
        try{
			return objectMapper.writeValueAsString(valueType);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static <T> T fromJson(String json, Class<T> valueType){
        try{
            return objectMapper.readValue(json, valueType);
        }catch (JsonProcessingException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static String addFieldsToJsonBody(String jsonBody, Map<String, Object> additionalFields) {
        try {
			Map<String, Object> jsonMap = objectMapper.readValue(jsonBody, Map.class);
           	jsonMap.putAll(additionalFields);
           	return objectMapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add fields to JSON body", e);
        }
    }



}
