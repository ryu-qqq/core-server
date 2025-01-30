package com.ryuqq.core.api.controller.v1.external.resolver;

import com.ryuqq.core.api.controller.v1.external.request.BuyMaEventRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.BuyMaProductInsertFailedRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.BuyMaProductInsertedRequestDto;
import com.ryuqq.core.utils.JsonUtils;

public class BuyMaEventFactory {

	public static BuyMaEventRequestDto getEventInfo(String eventType, String payload) {
		return switch (eventType) {
			case "product/create", "product/update" -> JsonUtils.fromJson(payload, BuyMaProductInsertedRequestDto.class);
			case "product/fail_to_create", "product/fail_to_update" -> JsonUtils.fromJson(payload, BuyMaProductInsertFailedRequestDto.class);
			default -> throw new IllegalArgumentException("Unsupported GitHub event type: "
				+ eventType);
		};
	}

}
