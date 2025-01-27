package com.ryuqq.core.external.oco;

import java.util.List;

import com.ryuqq.core.external.oco.request.OcoProductInsertRequestDto;

public record OcoProductGroupInsertRequestContext(
	OcoProductInsertRequestDto ocoProductInsertRequestDto,
	List<OcoOptionContext> ocoOptionContexts
) {
}
