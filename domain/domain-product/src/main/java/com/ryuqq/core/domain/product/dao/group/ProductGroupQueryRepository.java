package com.ryuqq.core.domain.product.dao.group;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;

public interface ProductGroupQueryRepository {

	boolean simpleQuery();

	ProductGroupContext fetchContextById(long productGroupId);

	List<? extends ProductGroupContext> fetchContextByCondition(ProductGroupSearchCondition productGroupSearchCondition);

	long countByCondition(ProductGroupSearchCondition productGroupSearchCondition);



}
