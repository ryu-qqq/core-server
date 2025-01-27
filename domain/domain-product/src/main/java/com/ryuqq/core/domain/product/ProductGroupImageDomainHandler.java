package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

@Component
public class ProductGroupImageDomainHandler {

	private final ProductGroupImageRegister productGroupImageRegister;
	private final ProductDetailDescriptionRegister productDetailDescriptionRegister;

	public ProductGroupImageDomainHandler(ProductGroupImageRegister productGroupImageRegister,
										  ProductDetailDescriptionRegister productDetailDescriptionRegister) {
		this.productGroupImageRegister = productGroupImageRegister;
		this.productDetailDescriptionRegister = productDetailDescriptionRegister;
	}


	public void handleProductImageDomain(ProductGroupImageBundle productGroupImageBundle){
		productGroupImageRegister.register(productGroupImageBundle);
	}

	public void handleProductDetailDescriptionDomain(ProductDetailDescription productDetailDescription){
		productDetailDescriptionRegister.register(productDetailDescription);
	}



}
