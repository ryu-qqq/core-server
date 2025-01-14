package com.ryuqq.core.storage.db.git.dto;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;

import com.querydsl.core.annotations.QueryProjection;

public class ChangedFileDto {

	private Long id;
	private long commitId;
	private String gitCommitId;
	private String className;
	private String filePath;
	private ChangeType changeType;
	private TestStatus status;

	@QueryProjection
	public ChangedFileDto(Long id, long commitId, String gitCommitId, String className, String filePath,
						  ChangeType changeType, TestStatus status) {
		this.id = id;
		this.commitId = commitId;
		this.gitCommitId = gitCommitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public long getCommitId() {
		return commitId;
	}

	public String getGitCommitId() {
		return gitCommitId;
	}

	public String getClassName() {
		return className;
	}

	public String getFilePath() {
		return filePath;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public TestStatus getStatus() {
		return status;
	}
}
