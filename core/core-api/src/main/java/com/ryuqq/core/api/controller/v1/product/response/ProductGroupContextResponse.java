package com.ryuqq.core.api.controller.v1.product.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = AdminProductGroupContextResponseDto.class, name = "ADMIN"),
	@JsonSubTypes.Type(value = UserProductGroupContextResponseDto.class, name = "USER"),
	@JsonSubTypes.Type(value = DefaultProductGroupContextResponseDto.class, name = "DEFAULT")
})
public interface ProductGroupContextResponse {



}
