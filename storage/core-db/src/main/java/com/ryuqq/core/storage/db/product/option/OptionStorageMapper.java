package com.ryuqq.core.storage.db.product.option;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.detail.OptionDetailCommand;
import com.ryuqq.core.domain.product.dao.options.group.OptionGroupCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;

@Component
public class OptionStorageMapper {

	public OptionGroupEntity toEntity(OptionGroupCommand optionGroupCommand){
		return new OptionGroupEntity(optionGroupCommand.name());
	}

	public OptionDetailEntity toEntity(OptionDetailCommand optionDetailCommand){
		return new OptionDetailEntity(optionDetailCommand.optionGroupId(), optionDetailCommand.optionValue());
	}

	public ProductOptionEntity toEntity(ProductOptionCommand productOptionCommand){
		return new ProductOptionEntity(
			productOptionCommand.productId(),
			productOptionCommand.optionGroupId(),
			productOptionCommand.optionDetailId()
		);
	}


}
