package com.ryuqq.core.domain.product.dao.options;

import java.util.List;

import com.ryuqq.core.domain.product.DefaultProductOptionContext;

public interface ProductQueryRepository {
	DefaultProductOptionContext fetchByProductGroupId(long productGroupId);
	List<DefaultProductOptionContext> fetchByProductGroupIds(List<Long> productGroupIds);

}
