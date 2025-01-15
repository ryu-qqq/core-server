package com.ryuqq.core.domain.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.storage.db.git.ChangedFileCommand;

public class ChangedFile {
	private Long id;
	private long commitId;
	private final String className;
	private final String filePath;
	private final ChangeType changeType;

	protected ChangedFile(String className, String filePath, ChangeType changeType) {
		this.id = 0L;
		this.commitId = 0L;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
	}

	protected ChangedFile(long commitId, String className, String filePath, ChangeType changeType) {
		this.id = 0L;
		this.commitId = commitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
	}

	public ChangedFile(long id, long commitId, String className, String filePath, ChangeType changeType) {
		this.id = id;
		this.commitId = commitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
	}

	protected ChangedFileCommand toCommand(long commitId){
		return new ChangedFileCommand(id, commitId, className, filePath, changeType);
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
