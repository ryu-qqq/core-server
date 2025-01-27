package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.dao.options.ExternalProductCommand;
import com.ryuqq.core.domain.external.dao.options.ExternalProductCommandFactory;
import com.ryuqq.core.domain.external.dao.options.ExternalProductPersistenceRepository;

@Component
public class ExternalProductRegister {

	private final ExternalProductPersistenceRepository externalProductPersistenceRepository;

	public ExternalProductRegister(ExternalProductPersistenceRepository externalProductPersistenceRepository) {
		this.externalProductPersistenceRepository = externalProductPersistenceRepository;
	}

	public void register(ExternalProduct externalProduct) {
		register(List.of(externalProduct));
	}

	public void register(List<ExternalProduct> externalProducts) {
		List<ExternalProductCommand> externalProductCommands = externalProducts.stream()
			.map(ExternalProductCommandFactory::toCommand)
			.toList();

		externalProductPersistenceRepository.saveAll(externalProductCommands);
	}

	public void update(ExternalProduct externalProduct) {
		updateAll(List.of(externalProduct));
	}

	public void updateAll(List<ExternalProduct> externalProducts) {
		List<ExternalProductCommand> externalProductCommands = externalProducts.stream()
			.map(ExternalProductCommandFactory::toCommand)
			.toList();

		externalProductPersistenceRepository.updateAll(externalProductCommands);
	}


}
