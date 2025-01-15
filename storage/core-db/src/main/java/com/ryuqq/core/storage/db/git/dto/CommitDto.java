package com.ryuqq.core.storage.db.git.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

public class CommitDto {
	private long id;
	private Long branchId;
	private String gitCommitId;
	private String author;
	private String commitMessage;
	private LocalDateTime timestamp;
	private List<ChangedFileDto> changedFiles;

	@QueryProjection
	public CommitDto(long id, Long branchId, String gitCommitId, String author, String commitMessage,
					 LocalDateTime timestamp, List<ChangedFileDto> changedFiles) {
		this.id = id;
		this.branchId = branchId;
		this.gitCommitId = gitCommitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
		this.changedFiles = changedFiles;
	}

	public long getId() {
		return id;
	}

	public Long getBranchId() {
		return branchId;
	}

	public String getGitCommitId() {
		return gitCommitId;
	}

	public String getAuthor() {
		return author;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public List<ChangedFileDto> getChangedFiles() {
		return changedFiles;
	}
}
