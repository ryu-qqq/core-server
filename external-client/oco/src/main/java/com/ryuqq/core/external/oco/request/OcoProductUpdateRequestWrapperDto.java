package com.ryuqq.core.external.oco.request;

public record OcoProductUpdateRequestWrapperDto(
	int pid,
	OcoProductInsertRequestDto product
){
}
