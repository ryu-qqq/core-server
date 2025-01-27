package com.ryuqq.core.storage.db.external;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupCommand;

@Component
public class ExternalProductGroupStorageMapper {


	public ExternalProductGroupEntity toEntity(ExternalProductGroupCommand externalProductGroupCommand){
		if(externalProductGroupCommand.id() != null){
			return new ExternalProductGroupEntity(
				externalProductGroupCommand.id(),
				externalProductGroupCommand.siteId(),
				externalProductGroupCommand.productGroupId(),
				externalProductGroupCommand.externalProductGroupId(),
				externalProductGroupCommand.brandId(),
				externalProductGroupCommand.categoryId(),
				externalProductGroupCommand.productName(),
				externalProductGroupCommand.regularPrice(),
				externalProductGroupCommand.currentPrice(),
				externalProductGroupCommand.status(),
				externalProductGroupCommand.fixedPrice(),
				externalProductGroupCommand.soldOut(),
				externalProductGroupCommand.displayed()
			);
		}
		return new ExternalProductGroupEntity(
			externalProductGroupCommand.siteId(),
			externalProductGroupCommand.productGroupId(),
			externalProductGroupCommand.externalProductGroupId(),
			externalProductGroupCommand.brandId(),
			externalProductGroupCommand.categoryId(),
			externalProductGroupCommand.productName(),
			externalProductGroupCommand.regularPrice(),
			externalProductGroupCommand.currentPrice(),
			externalProductGroupCommand.status(),
			externalProductGroupCommand.fixedPrice(),
			externalProductGroupCommand.soldOut(),
			externalProductGroupCommand.displayed()
		);
	}
}
