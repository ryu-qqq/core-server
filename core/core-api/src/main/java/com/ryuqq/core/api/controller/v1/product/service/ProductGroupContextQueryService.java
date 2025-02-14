package com.ryuqq.core.api.controller.v1.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.api.controller.v1.product.response.ProductGroupTopIdResponse;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;

@Service
public class ProductGroupContextQueryService {

	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;

	public ProductGroupContextQueryService(ProductGroupContextQueryInterface productGroupContextQueryInterface) {
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
	}

	public ProductGroupTopIdResponse fetchProductGroupTopId(){
		return new ProductGroupTopIdResponse(productGroupContextQueryInterface.fetchProductGroupTopId());
	}

	public ProductGroupContext fetchById(long productGroupId){
		return productGroupContextQueryInterface.fetchById(productGroupId);
	}

	public List<? extends ProductGroupContext> fetchByProductGroupIds(List<Long> productGroupIds){
		return productGroupContextQueryInterface.fetchByIds(productGroupIds);
	}



}
