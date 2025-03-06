package com.ryuqq.core.domain.external.core;

import com.ryuqq.core.domain.external.ExternalProductGroup;

public interface ExternalProductRequestMapper<T, U> {

	T toInsertRequestDto(ExternalProductGroup externalProductGroup);
	U toUpdateRequestDto(ExternalProductGroup externalProductGroup);
}
