package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductNoticeUpdateProcessor implements UpdateProcessor<ProductNotice> {

	private final ProductNoticeRegister productNoticeRegister;

	public ProductNoticeUpdateProcessor(ProductNoticeRegister productNoticeRegister) {
		this.productNoticeRegister = productNoticeRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductNotice.class.equals(domainType);
	}

	@Override
	public void processUpdate(ProductNotice entity) {
		productNoticeRegister.update(entity);
	}

}
