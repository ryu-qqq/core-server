package com.ryuqq.core.storage.db.git.dto;

import com.ryuqq.core.enums.ChangeType;

import com.querydsl.core.annotations.QueryProjection;

public class ChangedFileDto {

	private long id;
	private long commitId;
	private String className;
	private String filePath;
	private ChangeType changeTyp;

	@QueryProjection
	public ChangedFileDto(long id, long commitId, String className, String filePath, ChangeType changeTyp) {
		this.id = id;
		this.commitId = commitId;
		this.className = className;
		this.filePath = filePath;
		this.changeTyp = changeTyp;
	}

	public long getId() {
		return id;
	}

	public long getCommitId() {
		return commitId;
	}

	public String getClassName() {
		return className;
	}

	public String getFilePath() {
		return filePath;
	}

	public ChangeType getChangeTyp() {
		return changeTyp;
	}
}
