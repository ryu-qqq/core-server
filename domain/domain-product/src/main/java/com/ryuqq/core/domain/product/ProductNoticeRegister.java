package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.notice.ProductNoticeCommandFactory;
import com.ryuqq.core.domain.product.dao.notice.ProductNoticePersistenceRepository;

@Component
public class ProductNoticeRegister {

	private final ProductNoticePersistenceRepository productNoticePersistenceRepository;

	public ProductNoticeRegister(ProductNoticePersistenceRepository productNoticePersistenceRepository) {
		this.productNoticePersistenceRepository = productNoticePersistenceRepository;
	}

	public void register(ProductNotice productNotice){
		productNoticePersistenceRepository.save(ProductNoticeCommandFactory.createCommandFrom(productNotice));
	}

	public void update(ProductNotice productNotice){
		productNoticePersistenceRepository.update(ProductNoticeCommandFactory.createCommandFrom(productNotice));
	}



}
