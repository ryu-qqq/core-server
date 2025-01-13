package com.ryuqq.core.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper()
		.registerModule(new JavaTimeModule())
		.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);



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
