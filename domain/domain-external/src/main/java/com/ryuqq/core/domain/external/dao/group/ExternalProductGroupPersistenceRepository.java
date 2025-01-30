package com.ryuqq.core.domain.external.dao.group;

import java.util.List;

public interface ExternalProductGroupPersistenceRepository {

	void save(ExternalProductGroupCommand externalProductGroupCommand);
	void saveAll(List<ExternalProductGroupCommand> externalProductGroupCommands);
	void update(ExternalProductGroupCommand externalProductGroupCommand);
	void updateAll(List<ExternalProductGroupCommand> externalProductGroupCommands);

}
