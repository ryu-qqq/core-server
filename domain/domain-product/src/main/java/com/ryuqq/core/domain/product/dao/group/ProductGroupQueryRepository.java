package com.ryuqq.core.domain.product.dao.group;

import java.util.List;

import com.ryuqq.core.domain.product.DefaultProductGroup;
import com.ryuqq.core.domain.product.DefaultProductGroupContext;

public interface ProductGroupQueryRepository {

	DefaultProductGroupContext fetchContextById(long productGroupId);
	List<DefaultProductGroupContext> fetchContextByIds(List<Long> productGroupIds);
	List<DefaultProductGroup> fetchBySellerId(long sellerId);
}
