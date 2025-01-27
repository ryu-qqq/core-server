package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupCommand;
import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupCommandFactory;
import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupPersistenceRepository;

@Component
public class ExternalProductGroupRegister {

	private final ExternalProductGroupPersistenceRepository externalProductGroupPersistenceRepository;

	public ExternalProductGroupRegister(
		ExternalProductGroupPersistenceRepository externalProductGroupPersistenceRepository) {
		this.externalProductGroupPersistenceRepository = externalProductGroupPersistenceRepository;
	}

	public void register(ExternalProductGroup externalProductGroup) {
		register(List.of(externalProductGroup));
	}

	public void register(List<ExternalProductGroup> externalProductGroups) {
		List<ExternalProductGroupCommand> externalProductGroupCommands = externalProductGroups.stream()
			.map(ExternalProductGroupCommandFactory::toCommand)
			.toList();

		externalProductGroupPersistenceRepository.saveAll(externalProductGroupCommands);
	}

	public void update(ExternalProductGroup externalProductGroup) {
		updateAll(List.of(externalProductGroup));
	}

	public void updateAll(List<ExternalProductGroup> externalProductGroups) {
		List<ExternalProductGroupCommand> externalProductGroupCommands = externalProductGroups.stream()
			.map(ExternalProductGroupCommandFactory::toCommand)
			.toList();

		externalProductGroupPersistenceRepository.updateAll(externalProductGroupCommands);
	}


}
