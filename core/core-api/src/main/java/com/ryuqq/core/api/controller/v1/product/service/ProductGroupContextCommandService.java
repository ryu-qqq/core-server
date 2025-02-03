package com.ryuqq.core.api.controller.v1.product.service;

import org.springframework.stereotype.Service;

import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextFactory;
import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.controller.v1.product.validator.CentralValidator;
import com.ryuqq.core.api.controller.v1.product.validator.ValidationResult;
import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.enums.ErrorType;

@Service
public class ProductGroupContextCommandService {

	private final CentralValidator centralValidator;
	private final ProductGroupContextFactory productGroupContextFactory;
	private final ProductGroupContextDomainService productGroupContextDomainService;

	public ProductGroupContextCommandService(CentralValidator centralValidator,
											 ProductGroupContextFactory productGroupContextFactory,
											 ProductGroupContextDomainService productGroupContextDomainService) {
		this.centralValidator = centralValidator;
		this.productGroupContextFactory = productGroupContextFactory;
		this.productGroupContextDomainService = productGroupContextDomainService;
	}

	public long registerProductGroupContext(ProductGroupContextCommandRequestDto requestDto){
		validate(requestDto, false);
		ProductGroupContext productGroupContext = productGroupContextFactory.createFromDto(requestDto);
		return productGroupContextDomainService.registerProductGroupContext(productGroupContext);
	}

	public long updateProductGroupContext(long productGroupId, ProductGroupContextCommandRequestDto requestDto){
		validate(requestDto, true);
		ProductGroupContext productGroupContext = productGroupContextFactory.createFromDto(requestDto);
		productGroupContextDomainService.updateProductGroupContext(productGroupId, productGroupContext);
		return productGroupId;
	}

	private void validate(ProductGroupContextCommandRequestDto productGroupContextCommandRequestDto, boolean updated){
		ValidationResult result = centralValidator.validate(productGroupContextCommandRequestDto, updated);
		if(result.hasErrors()){
			throw new CoreException(ErrorType.BAD_REQUEST_ERROR, result.getErrorsToString());
		}
	}


}
