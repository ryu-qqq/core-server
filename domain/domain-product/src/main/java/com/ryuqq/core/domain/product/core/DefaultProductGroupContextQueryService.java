package com.ryuqq.core.domain.product.core;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.ProductGroupContextFinder;

@Service
class DefaultProductGroupContextQueryService implements ProductGroupContextQueryInterface {

	private final ProductGroupContextFinder productGroupContextFinder;

	public DefaultProductGroupContextQueryService(ProductGroupContextFinder productGroupContextFinder) {
		this.productGroupContextFinder = productGroupContextFinder;
	}

	@Override
	public ProductGroupContext fetchById(long productGroupId) {
		return productGroupContextFinder.fetchById(productGroupId);
	}

	@Override
	public List<? extends ProductGroupContext> fetchByCondition(
		ProductGroupSearchCondition productGroupSearchCondition) {
		return productGroupContextFinder.fetchByCondition(productGroupSearchCondition);
	}

	@Override
	public long countByCondition(ProductGroupSearchCondition productGroupSearchCondition) {
		return productGroupContextFinder.countByCondition(productGroupSearchCondition);
	}

}
