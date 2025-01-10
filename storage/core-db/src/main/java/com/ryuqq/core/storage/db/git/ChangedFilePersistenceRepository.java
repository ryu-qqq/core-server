package com.ryuqq.core.storage.db.git;

import java.util.List;

public interface ChangedFilePersistenceRepository {

	void saveAll(List<ChangedFileCommand> changedFileCommands);

}
