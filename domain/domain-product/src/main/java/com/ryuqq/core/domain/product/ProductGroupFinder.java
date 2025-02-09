package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.dao.group.ProductGroupQueryRepository;

@Service
public class ProductGroupFinder {

	private final ProductGroupQueryRepository productGroupQueryRepository;

	public ProductGroupFinder(ProductGroupQueryRepository productGroupQueryRepository) {
		this.productGroupQueryRepository = productGroupQueryRepository;
	}


	public DefaultProductGroupContext fetchNoProductContextById(long productGroupId) {
		return productGroupQueryRepository.fetchContextById(productGroupId);
	}

	public List<DefaultProductGroupContext> fetchNoProductContextByIds(List<Long> productGroupIds) {
		return productGroupQueryRepository.fetchContextByIds(productGroupIds);
	}

	public List<DefaultProductGroup> fetchProductGroupBySellerId(long sellerId){
		return productGroupQueryRepository.fetchBySellerId(sellerId);
	}

}
