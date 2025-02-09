package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.dao.options.ProductQueryRepository;

@Service
public class ProductFinder  {

	private final ProductQueryRepository productQueryRepository;

	public ProductFinder(ProductQueryRepository productQueryRepository) {
		this.productQueryRepository = productQueryRepository;
	}

	public DefaultProductOptionContext fetchByProductGroupId(long productGroupId) {
		return productQueryRepository.fetchByProductGroupId(productGroupId);
	}

	public List<DefaultProductOptionContext> fetchByProductGroupIds(List<Long> productGroupIds){
		return productQueryRepository.fetchByProductGroupIds(productGroupIds);
	}


}
