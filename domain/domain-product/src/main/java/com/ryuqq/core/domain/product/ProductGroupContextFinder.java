package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;

@Service
public class ProductGroupContextFinder {

	private final ProductGroupContextAssembler productGroupContextAssembler;

	public ProductGroupContextFinder(ProductGroupContextAssembler productGroupContextAssembler) {
		this.productGroupContextAssembler = productGroupContextAssembler;
	}

	public ProductGroupContext fetchById(long productGroupId){
		return productGroupContextAssembler.assemble(productGroupId);
	}

	public List<? extends ProductGroupContext> fetchByCondition(ProductGroupSearchCondition productGroupSearchCondition){
		return productGroupContextAssembler.assemble(productGroupSearchCondition);
	}

	public long countByCondition(ProductGroupSearchCondition productGroupSearchCondition){
		return productGroupContextAssembler.countByCondition(productGroupSearchCondition);
	}
}
