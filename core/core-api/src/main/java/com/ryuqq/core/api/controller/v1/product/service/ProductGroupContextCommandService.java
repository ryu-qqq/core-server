package com.ryuqq.core.api.controller.v1.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextCommandFactory;
import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextCommandFactoryProvider;
import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandInterface;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;

@Service
public class ProductGroupContextCommandService {

	private final ProductGroupContextCommandFactoryProvider productGroupContextCommandFactoryProvider;
	private final ProductGroupContextCommandInterface productGroupContextCommandInterface;

	public ProductGroupContextCommandService(
		ProductGroupContextCommandFactoryProvider productGroupContextCommandFactoryProvider,
		ProductGroupContextCommandInterface productGroupContextCommandInterface) {
		this.productGroupContextCommandFactoryProvider = productGroupContextCommandFactoryProvider;
		this.productGroupContextCommandInterface = productGroupContextCommandInterface;
	}

	@Transactional
	public long registerProductGroupContext(ProductGroupContextCommandRequestDto requestDto){
		ProductGroupContextCommandFactory provider = productGroupContextCommandFactoryProvider.getProvider();
		ProductGroupContextCommand productGroupContext = provider.createCommand(null, requestDto);
		return productGroupContextCommandInterface.save(productGroupContext);
	}

	@Transactional
	public long updateProductGroupContext(long productGroupId, ProductGroupContextCommandRequestDto requestDto){
		ProductGroupContextCommandFactory provider = productGroupContextCommandFactoryProvider.getProvider();
		ProductGroupContextCommand productGroupContext = provider.createCommand(productGroupId, requestDto);
		return productGroupContextCommandInterface.update(productGroupId, productGroupContext);
	}
}
