package com.ryuqq.core.external.sellic.request;

import java.util.List;

public record SellicProductStockUpdateWrapperDto(
	List<SellicProductStockUpdateRequestDto> datas
) {
}
