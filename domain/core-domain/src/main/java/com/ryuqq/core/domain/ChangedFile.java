package com.ryuqq.core.domain;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.CodeStatus;
import com.ryuqq.core.storage.db.git.ChangedFileCommand;

import java.util.Objects;

public class ChangedFile {
	private Long id;
	private long branchId;
	private String className;
	private String filePath;
	private ChangeType changeType;
	private CodeStatus status;
	private String commitId;
	private String commitMessage;

	public ChangedFile(String className, String filePath, ChangeType changeType, CodeStatus status,
					   String commitId, String commitMessage) {
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
		this.commitId = commitId;
		this.commitMessage = commitMessage;
	}

	public ChangedFile(Long id, long branchId, String className, String filePath, ChangeType changeType,
					   CodeStatus status,
					   String commitId, String commitMessage) {
		this.id = id;
		this.branchId = branchId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
		this.commitId = commitId;
		this.commitMessage = commitMessage;
	}

	public ChangedFileCommand toCommand(long branchId) {
		return new ChangedFileCommand(id, branchId, className, filePath, changeType, status, commitId, commitMessage);
	}

	public Long getId() {
		return id;
	}

	public long getBranchId() {
		return branchId;
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

	public CodeStatus getStatus() {
		return status;
	}

	public String getCommitId() {
		return commitId;
	}

	public String getCommitMessage() {
		return commitMessage;
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
		return branchId
			== that.branchId
			&& Objects.equals(id, that.id)
			&& Objects.equals(className, that.className)
			&& Objects.equals(filePath, that.filePath)
			&& changeType
			== that.changeType
			&& status
			== that.status
			&& Objects.equals(commitId, that.commitId)
			&& Objects.equals(commitMessage, that.commitMessage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, branchId, className, filePath, changeType, status, commitId, commitMessage);
	}

	@Override
	public String toString() {
		return "ChangedFile{"
			+
			"id="
			+ id
			+
			", branchId="
			+ branchId
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
			", commitId='"
			+ commitId
			+ '\''
			+
			", commitMessage='"
			+ commitMessage
			+ '\''
			+
			'}';
	}
}
