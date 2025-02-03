package com.ryuqq.core.api.controller.v1.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.domain.product.ProductGroupContextFinder;

@Service
public class ProductGroupContextQueryService {

	private final ProductGroupContextFinder productGroupContextFinder;

	public ProductGroupContextQueryService(ProductGroupContextFinder productGroupContextFinder) {
		this.productGroupContextFinder = productGroupContextFinder;
	}

	public ProductGroupContext fetchByProductGroupId(long productGroupId){
		return productGroupContextFinder.fetchById(productGroupId);
	}

	public List<ProductGroupContext> fetchByProductGroupIds(List<Long> productGroupIds){
		return productGroupContextFinder.fetchByIds(productGroupIds);
	}

}
