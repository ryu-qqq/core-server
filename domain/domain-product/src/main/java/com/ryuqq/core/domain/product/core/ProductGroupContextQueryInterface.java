package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductGroupContextQueryInterface {

	ProductGroupContext fetchById(long productGroupId);
	List<? extends ProductGroupContext> fetchByIds(List<Long> productGroupIds);

}
