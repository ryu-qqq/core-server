package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.ChangedFileDto;
import com.ryuqq.core.storage.db.git.dto.ChangedFileRequestStorageFilterDto;

import java.util.List;

public interface ChangedFileQueryRepository {

	List<ChangedFileDto> fetchByFilter(ChangedFileRequestStorageFilterDto filterDto);
	long countByFilter(ChangedFileRequestStorageFilterDto filterDto);

}
