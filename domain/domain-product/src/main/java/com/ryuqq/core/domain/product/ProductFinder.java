package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductContextInterface;
import com.ryuqq.core.domain.product.core.Sku;
import com.ryuqq.core.domain.product.dao.options.ProductQueryRepository;

@Component
public class ProductFinder implements ProductContextInterface {

	private final ProductQueryRepository productQueryRepository;

	public ProductFinder(ProductQueryRepository productQueryRepository) {
		this.productQueryRepository = productQueryRepository;
	}

	public ProductContextBundle fetchByProductGroupIds(List<Long> productGroupIds){
		return productQueryRepository.fetchByProductGroupIds(productGroupIds);
	}

	@Override
	public List<? extends Sku> fetchByProductGroupId(Long productGroupId) {
		return fetchByProductGroupIds(List.of(productGroupId)).getProducts();
	}

	@Override
	public List<? extends Sku> fetchByProductGroupId(List<Long> productGroupIds) {
		return fetchByProductGroupIds(productGroupIds).getProducts();
	}

}
