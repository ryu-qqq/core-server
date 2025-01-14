package com.ryuqq.core.domain.git;

import java.util.Objects;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.git.ChangedFileCommand;

public class ChangedFile {
	private Long id;
	private long commitId;
	private String gitCommitId;
	private String className;
	private String filePath;
	private ChangeType changeType;
	private TestStatus status;

	public ChangedFile(String gitCommitId, String className, String filePath, ChangeType changeType,
					   TestStatus status) {
		this.gitCommitId = gitCommitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
	}

	public ChangedFileCommand toCommand(long commitId) {
		return new ChangedFileCommand(id, commitId, gitCommitId, className, filePath, changeType, status);
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

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		ChangedFile that = (ChangedFile) object;
		return commitId
			== that.commitId
			&& Objects.equals(id, that.id)
			&& Objects.equals(gitCommitId, that.gitCommitId)
			&& Objects.equals(className, that.className)
			&& Objects.equals(filePath, that.filePath)
			&& changeType
			== that.changeType
			&& status
			== that.status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, commitId, gitCommitId, className, filePath, changeType, status);
	}

	@Override
	public String toString() {
		return "ChangedFile{"
			+
			"id="
			+ id
			+
			", commitId="
			+ commitId
			+
			", gitCommitId='"
			+ gitCommitId
			+ '\''
			+
			", className='"
			+ className
			+ '\''
			+
			", filePath='"
			+ filePath
			+ '\''
			+
			", changeType="
			+ changeType
			+
			", status="
			+ status
			+
			'}';
	}
}
