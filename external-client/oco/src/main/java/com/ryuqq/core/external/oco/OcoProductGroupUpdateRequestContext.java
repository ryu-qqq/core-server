package com.ryuqq.core.external.oco;

import java.util.List;

import com.ryuqq.core.external.oco.request.OcoProductInsertRequestDto;

public record OcoProductGroupUpdateRequestContext(
	OcoProductInsertRequestDto ocoProductInsertRequestDto,
	List<OcoOptionContext> ocoOptionContexts,
	List<OcoOptionContext> deletedOcoOptionContexts
	) {
}
