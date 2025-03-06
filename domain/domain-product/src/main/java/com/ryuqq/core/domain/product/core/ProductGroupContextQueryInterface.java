package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductGroupContextQueryInterface {

	ProductGroupContext fetchById(long productGroupId);
	List<? extends ProductGroupContext> fetchByCondition(ProductGroupSearchCondition productGroupSearchCondition);
	long countByCondition(ProductGroupSearchCondition productGroupSearchCondition);

}
