package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;


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

	public long registerProductGroupContext(ProductGroupContext productGroupContext){

		ProductGroupContext assignProductGroupIdContext = saveProductGroupAndAssignProductGroupId(productGroupContext);

		productGroupDomainHandler.handleProductDeliveryDomain(assignProductGroupIdContext.getProductDelivery());
		productGroupDomainHandler.handleProductNoticeDomain(assignProductGroupIdContext.getProductNotice());

		productGroupImageDomainHandler.handleProductImageDomain(assignProductGroupIdContext.getProductGroupImages());
		productGroupImageDomainHandler.handleProductDetailDescriptionDomain(assignProductGroupIdContext.getProductDetailDescription());

		productDomainHandler.handleProductDomain(assignProductGroupIdContext.getProducts());

		return assignProductGroupIdContext.getProductGroupId();
	}

	private ProductGroupContext saveProductGroupAndAssignProductGroupId(ProductGroupContext productGroupContext){
		long productGroupId = productGroupDomainHandler.handleProductGroupDomain(productGroupContext.getProductGroup());
		return productGroupContext.assignProductGroupId(productGroupId);
	}

}
