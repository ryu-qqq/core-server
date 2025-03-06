package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.domain.product.dao.group.ProductGroupQueryRepositoryProvider;

@Service
public class ProductGroupFinder {

	private final ProductGroupQueryRepositoryProvider productGroupQueryRepositoryProvider;

	public ProductGroupFinder(ProductGroupQueryRepositoryProvider productGroupQueryRepositoryProvider) {
		this.productGroupQueryRepositoryProvider = productGroupQueryRepositoryProvider;
	}

	public ProductGroupContext fetchProductContextById(long productGroupId) {
		return productGroupQueryRepositoryProvider
			.getProductGroupQueryRepository(true)
			.fetchContextById(productGroupId);
	}

	public List<? extends ProductGroupContext> fetchByCondition(ProductGroupSearchCondition productGroupSearchCondition) {
		return productGroupQueryRepositoryProvider
			.getProductGroupQueryRepository(productGroupSearchCondition.isSimpleQuery())
			.fetchContextByCondition(productGroupSearchCondition);
	}

	public long countByCondition(ProductGroupSearchCondition productGroupSearchCondition){
		return productGroupQueryRepositoryProvider
			.getProductGroupQueryRepository(productGroupSearchCondition.isSimpleQuery())
			.countByCondition(productGroupSearchCondition);
	}

}
