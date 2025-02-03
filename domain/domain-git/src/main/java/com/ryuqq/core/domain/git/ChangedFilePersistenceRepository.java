package com.ryuqq.core.domain.git;

import java.util.List;

public interface ChangedFilePersistenceRepository {

	void save(ChangedFileCommand changedFileCommand);
	void saveAll(List<ChangedFileCommand> changedFileCommands);

}
