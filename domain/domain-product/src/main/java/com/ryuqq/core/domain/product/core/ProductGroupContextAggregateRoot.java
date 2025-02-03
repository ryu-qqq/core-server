package com.ryuqq.core.domain.product.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.domain.product.ProductGroupContextRegister;
import com.ryuqq.core.domain.product.ProductGroupContextUpdater;

@Component
public class ProductGroupContextAggregateRoot {

	private final ProductGroupContextRegister productGroupContextRegister;
	private final ProductGroupContextUpdater productGroupContextUpdater;

	public ProductGroupContextAggregateRoot(ProductGroupContextRegister productGroupContextRegister,
											ProductGroupContextUpdater productGroupContextUpdater) {
		this.productGroupContextRegister = productGroupContextRegister;
		this.productGroupContextUpdater = productGroupContextUpdater;
	}

	public long registerProductGroupContext(ProductGroupContext productGroupContext){
		return productGroupContextRegister.registerProductGroupContext(productGroupContext);
	}

	public UpdateDecision updateProductGroupContext(long productGroupId, ProductGroupContext productGroupContext) {
		return productGroupContextUpdater.updateProductGroupContext(productGroupId, productGroupContext);
	}

}
