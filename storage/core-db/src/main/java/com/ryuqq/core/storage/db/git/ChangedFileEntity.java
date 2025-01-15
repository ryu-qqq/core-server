package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ChangeType;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "CHANGED_FILE")
@Entity
public class ChangedFileEntity extends BaseEntity {

	@Column(name = "COMMIT_ID", nullable = false)
	private long commitId;

	@Column(name = "CLASS_NAME", nullable = false, length = 255)
	private String className;

	@Column(name = "FILE_PATH", nullable = false, length = 255)
	private String filePath;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHANGE_TYPE", nullable = false)
	private ChangeType changeType;

	protected ChangedFileEntity() {}

	public ChangedFileEntity(long commitId, String className, String filePath, ChangeType changeType) {
		this.commitId = commitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
	}

	public ChangedFileEntity(long id, long commitId, String className, String filePath, ChangeType changeType) {
		this.id = id;
		this.commitId = commitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
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
