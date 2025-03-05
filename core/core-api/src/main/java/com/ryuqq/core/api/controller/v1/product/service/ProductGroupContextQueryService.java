package com.ryuqq.core.api.controller.v1.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;

@Service
public class ProductGroupContextQueryService {

	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;

	public ProductGroupContextQueryService(ProductGroupContextQueryInterface productGroupContextQueryInterface) {
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
	}

	public ProductGroupContext fetchById(long productGroupId){
		return productGroupContextQueryInterface.fetchById(productGroupId);
	}

	public List<? extends  ProductGroupContext> fetchByCondition(ProductGroupSearchCondition productGroupSearchCondition){
		return productGroupContextQueryInterface.fetchByCondition(productGroupSearchCondition);
	}


	public long countByCondition(ProductGroupSearchCondition productGroupSearchCondition){
		return productGroupContextQueryInterface.countByCondition(productGroupSearchCondition);

	}

}
