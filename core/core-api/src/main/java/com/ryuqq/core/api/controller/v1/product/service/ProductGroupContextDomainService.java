package com.ryuqq.core.api.controller.v1.product.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuqq.core.domain.exception.DataNotFoundException;
import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextAggregateRoot;
import com.ryuqq.core.domain.product.core.ProductGroupContextEventHandler;
import com.ryuqq.core.domain.product.core.UpdateDecision;

import javax.xml.crypto.Data;

@Service
public class ProductGroupContextDomainService {

	private final ProductGroupContextAggregateRoot productGroupContextAggregateRoot;
	private final ProductGroupContextEventHandler productGroupContextEventHandler;

	public ProductGroupContextDomainService(ProductGroupContextAggregateRoot productGroupContextAggregateRoot,
											ProductGroupContextEventHandler productGroupContextEventHandler) {
		this.productGroupContextAggregateRoot = productGroupContextAggregateRoot;
		this.productGroupContextEventHandler = productGroupContextEventHandler;
	}

	@Transactional
	public void registerProductGroupContext(ProductGroupContext productGroupContext) {
		long productGroupId = productGroupContextAggregateRoot.registerProductGroupContext(productGroupContext);
		productGroupContextEventHandler.handleEvents(
			productGroupContext.getSellerId(), productGroupId,
			productGroupContext.getBrandId(), productGroupContext.getCategoryId()
		);
	}

	@Transactional
	public void updateProductGroupContext(long productGroupId, ProductGroupContext productGroupContext) {
		try{
			UpdateDecision updateDecision = productGroupContextAggregateRoot.updateProductGroupContext(productGroupId,
				productGroupContext);

			productGroupContextEventHandler.handleEvents(
				productGroupContext.getSellerId(), productGroupContext.getProductGroupId(),
				productGroupContext.getBrandId(), productGroupContext.getCategoryId(),
				updateDecision);
		}catch (Exception e) {
			registerProductGroupContext(productGroupContext);
		}
	}
}
