package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;


@Component
public class ProductGroupContextRegister {

	private final ProductGroupDomainHandler productGroupDomainHandler;
	private final ProductGroupImageDomainHandler productGroupImageDomainHandler;
	private final ProductDomainHandler productDomainHandler;

	public ProductGroupContextRegister(ProductGroupDomainHandler productGroupDomainHandler,
									   ProductGroupImageDomainHandler productGroupImageDomainHandler,
									   ProductDomainHandler productDomainHandler) {
		this.productGroupDomainHandler = productGroupDomainHandler;
		this.productGroupImageDomainHandler = productGroupImageDomainHandler;
		this.productDomainHandler = productDomainHandler;
	}

	public long registerProductGroupContext(ProductGroupContextCommand productGroupContextCommand){
		long productGroupId = productGroupDomainHandler.handle(productGroupContextCommand.getEssentialProductGroupInfo());
		productGroupImageDomainHandler.handle(productGroupId, productGroupContextCommand.getEssentialProductImageInfo());
		productDomainHandler.handle(productGroupId, productGroupContextCommand.getProductCommandContextCommand());
		return productGroupId;
	}

}
