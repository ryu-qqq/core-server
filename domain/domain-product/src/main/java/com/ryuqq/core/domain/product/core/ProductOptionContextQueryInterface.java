package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductOptionContextQueryInterface {

	ProductOptionContext fetchByProductGroupId(Long productGroupId);
	List<? extends ProductOptionContext> fetchByProductGroupId(List<Long> productGroupIds);

}
