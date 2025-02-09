package com.ryuqq.core.domain.product.core;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.ProductFinder;

@Service
public class DefaultProductOptionContextQueryService implements ProductOptionContextQueryInterface {

	private final ProductFinder productFinder;

	public DefaultProductOptionContextQueryService(ProductFinder productFinder) {
		this.productFinder = productFinder;
	}

	@Override
	public ProductOptionContext fetchByProductGroupId(Long productGroupId) {
		return productFinder.fetchByProductGroupId(productGroupId);
	}

	@Override
	public List<? extends ProductOptionContext> fetchByProductGroupId(List<Long> productGroupIds) {
		return productFinder.fetchByProductGroupIds(productGroupIds);
	}
}
