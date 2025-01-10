package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.CodeStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Table(name = "CHANGED_FILE")
@Entity
public class ChangedFileEntity extends BaseEntity {

	@Column(name = "BRANCH_ID", nullable = false)
	private long branchId;

	@Column(name = "CLASS_NAME", nullable = false, length = 255)
	private String className;

	@Column(name = "FILE_PATH", nullable = false, length = 255)
	private String filePath;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHANGE_TYPE", nullable = false)
	private ChangeType changeType;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private CodeStatus status;

	@Column(name = "COMMIT_ID", nullable = false, length = 255)
	private String commitId;

	@Lob
	@Column(name = "COMMIT_MESSAGE")
	private String commitMessage;


	protected ChangedFileEntity() {}

	public ChangedFileEntity(long branchId, String className, String filePath, ChangeType changeType, CodeStatus status,
							 String commitId, String commitMessage) {
		this.branchId = branchId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
		this.commitId = commitId;
		this.commitMessage = commitMessage;
	}

	public ChangedFileEntity(long id, long branchId, String className, String filePath, ChangeType changeType, CodeStatus status,
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
}
