package com.ryuqq.core.storage.db.git;

import java.util.List;

public interface ChangedFilePersistenceRepository {

	void save(ChangedFileCommand changedFileCommand);
	void saveAll(List<ChangedFileCommand> changedFileCommands);

}
