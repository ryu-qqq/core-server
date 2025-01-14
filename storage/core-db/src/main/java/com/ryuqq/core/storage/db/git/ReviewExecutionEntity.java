package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "REVIEW_EXECUTION")
public class ReviewExecutionEntity extends BaseEntity {

	@Column(name = "COMMIT_ID", nullable = false)
	private Long commitId;

	@Column(name = "CHANGED_FILE_ID", nullable = false)
	private Long changedFileId;

	@Column(name = "REVIEWER", nullable = false, length = 255)
	private String reviewer;

	@Enumerated(EnumType.STRING)
	@Column(name = "REVIEW_STATUS", nullable = false, length = 20)
	private ReviewStatus reviewStatus;


	@Enumerated(EnumType.STRING)
	@Column(name = "TEST_STATUS", nullable = false, length = 20)
	private TestStatus testStatus;

	@Lob
	@Column(name = "COMMENTS", nullable = false)
	private String comments;

	@Lob
	@Column(name = "TEST_LOGS", nullable = false)
	private String testLogs;

	protected ReviewExecutionEntity() {}

	public ReviewExecutionEntity(Long commitId, Long changedFileId, TestStatus testStatus, ReviewStatus reviewStatus) {
		this.commitId = commitId;
		this.changedFileId = changedFileId;
		this.testStatus = testStatus;
		this.reviewStatus = reviewStatus;
	}

	public ReviewExecutionEntity(Long commitId, Long changedFileId, String reviewer, ReviewStatus reviewStatus,
								 TestStatus testStatus, String comments, String testLogs) {
		this.commitId = commitId;
		this.changedFileId = changedFileId;
		this.reviewer = reviewer;
		this.reviewStatus = reviewStatus;
		this.testStatus = testStatus;
		this.comments = comments;
		this.testLogs = testLogs;
	}

	public Long getCommitId() {
		return commitId;
	}

	public Long getChangedFileId() {
		return changedFileId;
	}

	public String getReviewer() {
		return reviewer;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public TestStatus getTestStatus() {
		return testStatus;
	}

	public String getComments() {
		return comments;
	}

	public String getTestLogs() {
		return testLogs;
	}
}
