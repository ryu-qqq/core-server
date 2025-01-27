package com.ryuqq.core.domain.product.dao.group;

import java.util.List;

import com.ryuqq.core.domain.product.ProductGroup;
import com.ryuqq.core.domain.product.ProductGroupContext;

public interface ProductGroupQueryRepository {

	ProductGroupContext fetchContextById(long productGroupId);
	List<ProductGroupContext> fetchContextByIds(List<Long> productGroupIds);
	List<ProductGroup> fetchBySellerId(long sellerId);
}
