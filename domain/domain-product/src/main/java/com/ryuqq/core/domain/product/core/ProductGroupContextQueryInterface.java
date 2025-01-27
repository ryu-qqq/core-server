package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductGroupContextQueryInterface {

	ItemContext fetchByProductGroupId(Long productGroupId);
	List<? extends ItemContext> fetchByProductGroupIds(List<Long> productGroupIds);
}
