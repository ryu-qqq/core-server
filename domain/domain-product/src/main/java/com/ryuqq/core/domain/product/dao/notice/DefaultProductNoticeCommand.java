package com.ryuqq.core.domain.product.dao.notice;

import com.ryuqq.core.enums.Origin;

public record DefaultProductNoticeCommand(
	long productGroupId,
	String material,
	String color,
	String size,
	String maker,
	Origin origin,
	String washingMethod,
	String yearMonth,
	String assuranceStandard,
	String asPhone
) implements ProductNoticeCommand{

}
