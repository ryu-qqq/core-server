package com.ryuqq.core.external.oco;

import com.ryuqq.core.external.oco.request.OcoOptionInsertRequestDto;

public record OcoOptionContext(
	long productId,
	OcoOptionInsertRequestDto ocoOptionInsertRequestDto,
	boolean deleted
) {
}
