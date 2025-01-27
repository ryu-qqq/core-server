package com.ryuqq.core.api.controller.v1.product.response;

import java.util.List;

public record ProductGroupTopIdResponse(
	List<Long> productGroupIds
) {
}
