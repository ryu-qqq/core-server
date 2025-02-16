package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;

@Component
public class ProductGroupImageDomainHandler {

	private final ProductGroupImageRegister productGroupImageRegister;
	private final ProductDetailDescriptionRegister productDetailDescriptionRegister;

	public ProductGroupImageDomainHandler(ProductGroupImageRegister productGroupImageRegister,
										  ProductDetailDescriptionRegister productDetailDescriptionRegister) {
		this.productGroupImageRegister = productGroupImageRegister;
		this.productDetailDescriptionRegister = productDetailDescriptionRegister;
	}

	public void handle(long productGroupId, ProductGroupContextCommand.EssentialProductImageInfo productImageInfo){
		handleProductImageDomain(productGroupId, productImageInfo.productGroupImageContextCommand());
		handleProductDetailDescriptionDomain(productGroupId, productImageInfo.productDetailDescription());
	}

	private void handleProductImageDomain(long productGroupId, ProductGroupImageContextCommand productGroupImageContextCommand){
		ProductGroupImageContextCommand assignedProductGroupImageContextCommand = productGroupImageContextCommand.assignProductGroupId(productGroupId);
		productGroupImageRegister.register(assignedProductGroupImageContextCommand.productGroupImageCommands());
	}

	private void handleProductDetailDescriptionDomain(long productGroupId, ProductDetailDescriptionCommand productDetailDescriptionCommand){
		ProductDetailDescriptionCommand assignProductDetailDescriptionCommand = productDetailDescriptionCommand.assignProductGroupId(productGroupId);
		productDetailDescriptionRegister.register(assignProductDetailDescriptionCommand);
	}



}
