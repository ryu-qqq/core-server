package com.ryuqq.core.domain.git;

public interface MergeReportPersistenceRepository {

	void save(MergeReportCommand mergeReportCommand);

}
