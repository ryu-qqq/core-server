package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;

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
	public List<? extends ProductGroupContext> fetchByIds(List<Long> productGroupIds) {
		return productGroupContextFinder.fetchByIds(productGroupIds);
	}

}
