package com.ryuqq.core.api.controller.v1.brand.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = AdminBrandContextResponseDto.class, name = "ADMIN"),
	@JsonSubTypes.Type(value = UserBrandContextResponseDto.class, name = "USER"),
	@JsonSubTypes.Type(value = DefaultBrandContextResponseDto.class, name = "DEFAULT")
})
public interface BrandContextResponse {

}
