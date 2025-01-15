package com.ryuqq.core.storage.db.git.dto;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;

public class ReviewExecutionDto {

	private Long commitId;
	private Long changedFileId;
	private ReviewStatus reviewStatus;
	private TestStatus testStatus;
	private String resultPath;

}
