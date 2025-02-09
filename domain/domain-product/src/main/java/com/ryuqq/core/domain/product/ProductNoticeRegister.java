package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductNoticeCommand;
import com.ryuqq.core.domain.product.dao.notice.ProductNoticePersistenceRepository;

@Component
public class ProductNoticeRegister {

	private final ProductNoticePersistenceRepository productNoticePersistenceRepository;

	public ProductNoticeRegister(ProductNoticePersistenceRepository productNoticePersistenceRepository) {
		this.productNoticePersistenceRepository = productNoticePersistenceRepository;
	}

	public void register(ProductNoticeCommand productNoticeCommand){
		productNoticePersistenceRepository.save(productNoticeCommand);
	}

	public void update(ProductNoticeCommand productNoticeCommand){
		productNoticePersistenceRepository.update(productNoticeCommand);
	}

}
