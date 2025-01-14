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

	@Column(name = "GIT_COMMIT_ID", nullable = false, length = 255)
	private String gitCommitId;

	@Column(name = "CLASS_NAME", nullable = false, length = 255)
	private String className;

	@Column(name = "FILE_PATH", nullable = false, length = 255)
	private String filePath;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHANGE_TYPE", nullable = false)
	private ChangeType changeType;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private TestStatus status;

	protected ChangedFileEntity() {}

	public ChangedFileEntity(long commitId, String gitCommitId, String className, String filePath,
							 ChangeType changeType,
							 TestStatus status) {
		this.commitId = commitId;
		this.gitCommitId = gitCommitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
	}

	public ChangedFileEntity(long id, long commitId, String gitCommitId, String className, String filePath,
							 ChangeType changeType,
							 TestStatus status) {
		this.id = id;
		this.commitId = commitId;
		this.gitCommitId = gitCommitId;
		this.className = className;
		this.filePath = filePath;
		this.changeType = changeType;
		this.status = status;
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

	public TestStatus getStatus() {
		return status;
	}

	public String getGitCommitId() {
		return gitCommitId;
	}

}
