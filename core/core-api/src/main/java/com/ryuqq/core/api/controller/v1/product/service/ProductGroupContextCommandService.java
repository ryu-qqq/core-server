package com.ryuqq.core.api.controller.v1.product.service;

import org.springframework.stereotype.Service;

import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextFactory;
import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.controller.v1.product.validator.CentralValidator;
import com.ryuqq.core.api.controller.v1.product.validator.ValidationResult;
import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.domain.product.dao.sync.DefaultProductSyncCommand;
import com.ryuqq.core.domain.product.dao.sync.ProductSyncPersistenceRepository;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.enums.SyncStatus;

@Service
public class ProductGroupContextCommandService {

	private final CentralValidator centralValidator;
	private final ProductGroupContextFactory productGroupContextFactory;
	private final ProductGroupContextDomainService productGroupContextDomainService;
	private final ProductSyncPersistenceRepository productSyncPersistenceRepository;

	public ProductGroupContextCommandService(CentralValidator centralValidator,
											 ProductGroupContextFactory productGroupContextFactory,
											 ProductGroupContextDomainService productGroupContextDomainService,
											 ProductSyncPersistenceRepository productSyncPersistenceRepository) {
		this.centralValidator = centralValidator;
		this.productGroupContextFactory = productGroupContextFactory;
		this.productGroupContextDomainService = productGroupContextDomainService;
		this.productSyncPersistenceRepository = productSyncPersistenceRepository;
	}

	public long registerProductGroupContext(ProductGroupContextCommandRequestDto requestDto){
		try{
			validate(requestDto, false);

			ProductGroupContext productGroupContext = productGroupContextFactory.createFromDto(requestDto);

			productGroupContextDomainService.registerProductGroupContext(productGroupContext);

			return requestDto.productGroup().productGroupId();
		}catch (Exception e){
			DefaultProductSyncCommand defaultProductSyncCommand = new DefaultProductSyncCommand(
				requestDto.productGroup().productGroupId(), SyncStatus.FAILED);
			productSyncPersistenceRepository.save(defaultProductSyncCommand);
			return 0L;
		}
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
