package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.notice.ProductNoticeCommand;

@Component
public class ProductNoticeUpdateProcessor implements UpdateProcessor<ProductNoticeCommand> {

	private final ProductNoticeRegister productNoticeRegister;

	public ProductNoticeUpdateProcessor(ProductNoticeRegister productNoticeRegister) {
		this.productNoticeRegister = productNoticeRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductNoticeCommand.class.isAssignableFrom(domainType);
	}

	@Override
	public void processUpdate(ProductNoticeCommand productNoticeCommand) {
		productNoticeRegister.update(productNoticeCommand);
	}

}
