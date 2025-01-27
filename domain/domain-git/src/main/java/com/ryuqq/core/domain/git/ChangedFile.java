package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;

public class ChangedFile {
	private Long id;
	private Long commitId;
	private final String className;
	private final String filePath;
	private final ChangeType changeType;

	private ChangedFile(Long id, Long commitId, String className, String filePath, ChangeType changeType) {
		this.id = id;
		this.commitId = commitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
	}

	public static ChangedFile create(String className, String filePath, ChangeType changeType) {
		return new ChangedFile(null, null, className, filePath, changeType);
	}

	public static ChangedFile create(Long id, long commitId, String className, String filePath, ChangeType changeType) {
		return new ChangedFile(id, commitId, className, filePath, changeType);
	}


	protected ChangedFileCommand toCommand(long commitId){
		if(id != null){
			return new UpdateChangedFileCommand(id, commitId, className, filePath, changeType);
		}

		return new CreateChangedFileCommand(commitId, className, filePath, changeType);
	}

	public Long getId() {
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

	public ChangeType getChangeType() {
		return changeType;
	}
}
