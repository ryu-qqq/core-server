package com.ryuqq.core.domain.product.core;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.ProductGroupContextRegister;
import com.ryuqq.core.domain.product.ProductGroupContextUpdater;
import com.ryuqq.core.enums.ErrorType;

@Service
public class DefaultProductGroupContextCommandService implements ProductGroupContextCommandInterface {

	private final ProductGroupDomainBusinessValidator productGroupDomainBusinessValidator;
	private final ProductGroupContextRegister productGroupContextRegister;
	private final ProductGroupContextUpdater productGroupContextUpdater;
	private final ProductGroupContextEventHandler productGroupContextEventHandler;

	public DefaultProductGroupContextCommandService(
		ProductGroupDomainBusinessValidator productGroupDomainBusinessValidator,
		ProductGroupContextRegister productGroupContextRegister, ProductGroupContextUpdater productGroupContextUpdater,
		ProductGroupContextEventHandler productGroupContextEventHandler) {
		this.productGroupDomainBusinessValidator = productGroupDomainBusinessValidator;
		this.productGroupContextRegister = productGroupContextRegister;
		this.productGroupContextUpdater = productGroupContextUpdater;
		this.productGroupContextEventHandler = productGroupContextEventHandler;
	}

	@Override
	public long save(ProductGroupContextCommand productGroupContextCommand) {
		productGroupDomainBusinessValidator.validate(productGroupContextCommand, false);
		long id = productGroupContextRegister.registerProductGroupContext(productGroupContextCommand);
		productGroupContextEventHandler.handleEvents(id, productGroupContextCommand.getProductGroupCommand());
		return id;
	}

	@Override
	public long update(long id, ProductGroupContextCommand productGroupContextCommand) {
		productGroupDomainBusinessValidator.validate(productGroupContextCommand, true);
		try {
			UpdateDecision updateDecision = productGroupContextUpdater.updateProductGroupContext(id,
				productGroupContextCommand);

			productGroupContextEventHandler.handleEvents(id, productGroupContextCommand.getProductGroupCommand(), updateDecision);
			return id;

		}catch (DomainException e){
			if(e.getErrorType().equals(ErrorType.NOT_FOUND_ERROR)){
				return save(productGroupContextCommand);
			}
			throw e;
		}
	}

}
