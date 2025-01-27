package com.ryuqq.core.storage.db.external;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.dao.options.ExternalProductCommand;

@Component
public class ExternalProductStorageMapper {

	public ExternalProductEntity toEntity(ExternalProductCommand externalProductCommand){
		return new ExternalProductEntity(
			externalProductCommand.externalProductGroupId(),
			externalProductCommand.externalProductId(),
			externalProductCommand.productId(),
			externalProductCommand.optionValue(),
			externalProductCommand.quantity(),
			externalProductCommand.additionalPrice(),
			externalProductCommand.soldOut(),
			externalProductCommand.displayed()
		);
	}
}
