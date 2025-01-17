package com.ryuqq.core.domain.git;

import java.time.LocalDateTime;
import java.util.List;

import com.ryuqq.core.storage.db.git.CommitCommand;

public class Commit {
	private Long id;
	private long branchId;
	private String gitCommitId;
	private String author;
	private String commitMessage;
	private LocalDateTime timestamp;
	private List<ChangedFile> changedFiles;

	protected Commit(String gitCommitId, String author, String commitMessage, LocalDateTime timestamp,
				  List<ChangedFile> changedFiles) {
		this.id = 0L;
		this.branchId = 0L;
		this.gitCommitId = gitCommitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
		this.changedFiles = changedFiles;
	}

	protected Commit(long branchId, String gitCommitId, String author, String commitMessage, LocalDateTime timestamp,
					 List<ChangedFile> changedFiles) {
		this.id = 0L;
		this.branchId = branchId;
		this.gitCommitId = gitCommitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
		this.changedFiles = changedFiles;
	}

	protected Commit(Long id, long branchId, String gitCommitId, String author, String commitMessage,
				  LocalDateTime timestamp,
				  List<ChangedFile> changedFiles) {
		this.id = id;
		this.branchId = branchId;
		this.gitCommitId = gitCommitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
		this.changedFiles = changedFiles;
	}

	public CommitCommand toCommand(long branchId){
		return new CommitCommand(id, branchId, gitCommitId, author, commitMessage, timestamp);
	}

	public Long getId() {
		return id;
	}

	public long getBranchId() {
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

	public List<ChangedFile> getChangedFiles() {
		return changedFiles;
	}
}
