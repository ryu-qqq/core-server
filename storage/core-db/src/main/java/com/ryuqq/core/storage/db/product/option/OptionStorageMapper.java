package com.ryuqq.core.storage.db.product.option;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.domain.product.core.OptionDetailCommand;
import com.ryuqq.core.domain.product.core.OptionGroupCommand;

@Component
public class OptionStorageMapper {

	public OptionGroupEntity toEntity(OptionGroupCommand optionGroupCommand){
		return new OptionGroupEntity(optionGroupCommand.optionName());
	}

	public OptionDetailEntity toEntity(OptionDetailCommand optionDetailCommand){
		return new OptionDetailEntity(optionDetailCommand.optionGroupId(), optionDetailCommand.optionValue());
	}

	public ProductOptionEntity toEntity(OptionContextCommand optionContextCommand){
		return new ProductOptionEntity(
			optionContextCommand.productId(),
			optionContextCommand.optionGroupId(),
			optionContextCommand.optionDetailId()
		);
	}


}
