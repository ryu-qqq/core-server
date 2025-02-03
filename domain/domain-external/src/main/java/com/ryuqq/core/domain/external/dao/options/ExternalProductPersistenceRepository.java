package com.ryuqq.core.domain.external.dao.options;

import java.util.List;

public interface ExternalProductPersistenceRepository {

	void save(ExternalProductCommand externalProductCommand);
	void saveAll(List<ExternalProductCommand> externalProductCommands);
	void update(ExternalProductCommand externalProductCommand);
	void updateAll(List<ExternalProductCommand> externalProductCommands);
}
