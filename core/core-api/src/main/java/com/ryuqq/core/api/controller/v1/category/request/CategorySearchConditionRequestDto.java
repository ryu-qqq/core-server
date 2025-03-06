package com.ryuqq.core.api.controller.v1.category.request;


public record CategorySearchConditionRequestDto(
	Integer page,
	Integer size,
	Long cursorId

) {
}
