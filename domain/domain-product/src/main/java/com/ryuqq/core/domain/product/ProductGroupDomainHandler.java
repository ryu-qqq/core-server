package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

@Component
public class ProductGroupDomainHandler {

	private final ProductGroupRegister productGroupRegister;
	private final ProductDeliveryRegister productDeliveryRegister;
	private final ProductNoticeRegister productNoticeRegister;

	public ProductGroupDomainHandler(ProductGroupRegister productGroupRegister,
									 ProductDeliveryRegister productDeliveryRegister,
									 ProductNoticeRegister productNoticeRegister) {
		this.productGroupRegister = productGroupRegister;
		this.productDeliveryRegister = productDeliveryRegister;
		this.productNoticeRegister = productNoticeRegister;
	}

	public long handleProductGroupDomain(ProductGroup productGroup) {
		return productGroupRegister.register(productGroup);
	}

	public void handleProductDeliveryDomain(ProductDelivery productDelivery) {
		productDeliveryRegister.register(productDelivery);
	}

	public void handleProductNoticeDomain(ProductNotice productNotice) {
		productNoticeRegister.register(productNotice);
	}

}
