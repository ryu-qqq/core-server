package com.ryuqq.core.storage.db.product.group;

import java.util.List;
import java.util.Optional;

import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;

public interface ProductGroupQueryDslRepository {

	Optional<ProductGroupContextDto> fetchContextById(long productGroupId);
	List<ProductGroupContextDto> fetchContextByCondition(ProductGroupSearchCondition productGroupSearchCondition);
	long countByCondition(ProductGroupSearchCondition productGroupSearchCondition);
}
