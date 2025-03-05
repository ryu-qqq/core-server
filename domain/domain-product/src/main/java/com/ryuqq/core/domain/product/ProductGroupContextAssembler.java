package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;

@Service
public class ProductGroupContextAssembler {

	private final ProductGroupFinder productGroupFinder;
	private final ProductContextAssembler productContextAssembler;

	public ProductGroupContextAssembler(ProductGroupFinder productGroupFinder,
										ProductContextAssembler productContextAssembler) {
		this.productGroupFinder = productGroupFinder;
		this.productContextAssembler = productContextAssembler;
	}

	public ProductGroupContext assemble(long productGroupId) {
		ProductGroupContext productGroupContext = productGroupFinder.fetchProductContextById(productGroupId);
		return productContextAssembler.assembleWithProducts(productGroupContext);
	}

	public List<ProductGroupContext> assemble(ProductGroupSearchCondition productGroupSearchCondition) {
		List<? extends ProductGroupContext> productGroupContexts = productGroupFinder.fetchByCondition(productGroupSearchCondition);

		if(productGroupContexts.isEmpty()) {
			return List.of();
		}

		return productContextAssembler.assembleWithProducts(productGroupContexts);
	}


	public long countByCondition(ProductGroupSearchCondition productGroupSearchCondition){
		return productGroupFinder.countByCondition(productGroupSearchCondition);
	}



}
