package com.ryuqq.core.domain.product.dao.options;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductOptionContext;

public interface ProductQueryRepository {
	ProductOptionContext fetchByProductGroupId(long productGroupId);
	List<? extends ProductOptionContext> fetchByProductGroupIds(List<Long> productGroupIds);
}
