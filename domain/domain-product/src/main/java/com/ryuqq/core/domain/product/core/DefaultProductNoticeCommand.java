package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.enums.Origin;

record DefaultProductNoticeCommand(long productGroupId, String material, String color, String size, String maker,
										  Origin origin, String washingMethod, String yearMonth,
										  String assuranceStandard, String asPhone) implements ProductNoticeCommand {


	@Override
	public DefaultProductNoticeCommand assignProductGroupId(long productGroupId) {
		return new DefaultProductNoticeCommand(
			productGroupId, material, color,
			size, maker, origin, washingMethod, yearMonth, assuranceStandard ,asPhone
		);
	}

}
