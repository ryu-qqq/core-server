package com.ryuqq.core.domain.product.core;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.ProductGroupContextFinder;
import com.ryuqq.core.domain.product.ProductGroupFinder;

@Service
class DefaultProductGroupContextQueryService implements ProductGroupContextQueryInterface {

	private final ProductGroupContextFinder productGroupContextFinder;
	private final ProductGroupFinder productGroupFinder;

	public DefaultProductGroupContextQueryService(ProductGroupContextFinder productGroupContextFinder,
												  ProductGroupFinder productGroupFinder) {
		this.productGroupContextFinder = productGroupContextFinder;
		this.productGroupFinder = productGroupFinder;
	}

	@Override
	public long fetchProductGroupTopId() {
		return productGroupFinder.fetchTopId();
	}

	@Override
	public ProductGroupContext fetchById(long productGroupId) {
		return productGroupContextFinder.fetchById(productGroupId);
	}

	@Override
	public List<? extends ProductGroupContext> fetchByIds(List<Long> productGroupIds) {
		return productGroupContextFinder.fetchByIds(productGroupIds);
	}

}
