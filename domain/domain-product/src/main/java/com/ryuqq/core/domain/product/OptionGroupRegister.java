package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.group.OptionGroupPersistenceRepository;


@Component
public class OptionGroupRegister {

	private final OptionGroupPersistenceRepository optionGroupPersistenceRepository;

	public OptionGroupRegister(OptionGroupPersistenceRepository optionGroupPersistenceRepository) {
		this.optionGroupPersistenceRepository = optionGroupPersistenceRepository;
	}

	public long register(OptionGroup optionGroup) {
		return optionGroupPersistenceRepository.save(optionGroup.toCommand());
	}

}
