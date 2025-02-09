package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.OptionDetailCommand;
import com.ryuqq.core.domain.product.dao.options.detail.OptionDetailPersistenceRepository;


@Component
public class OptionDetailRegister {

	private final OptionDetailPersistenceRepository optionDetailPersistenceRepository;

	public OptionDetailRegister(OptionDetailPersistenceRepository optionDetailPersistenceRepository) {
		this.optionDetailPersistenceRepository = optionDetailPersistenceRepository;
	}

	public long register(OptionDetailCommand optionDetailCommand) {
		return optionDetailPersistenceRepository.save(optionDetailCommand);
	}

}
