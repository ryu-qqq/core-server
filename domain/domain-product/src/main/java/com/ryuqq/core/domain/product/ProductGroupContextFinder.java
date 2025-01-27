package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ItemContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;

@Component
public class ProductGroupContextFinder implements ProductGroupContextQueryInterface {

	private final ProductGroupContextAssembler productGroupContextAssembler;

	public ProductGroupContextFinder(ProductGroupContextAssembler productGroupContextAssembler) {
		this.productGroupContextAssembler = productGroupContextAssembler;
	}

	public ProductGroupContext fetchById(long productGroupId){
		return productGroupContextAssembler.assemble(productGroupId);
	}

	public List<ProductGroupContext> fetchByIds(List<Long> productGroupIds){
		return productGroupContextAssembler.assemble(productGroupIds);
	}

	@Override
	public ItemContext fetchByProductGroupId(Long productGroupId) {
		return fetchById(productGroupId);
	}

	@Override
	public List<? extends ItemContext> fetchByProductGroupIds(List<Long> productGroupIds) {
		return fetchByIds(productGroupIds);
	}

}
