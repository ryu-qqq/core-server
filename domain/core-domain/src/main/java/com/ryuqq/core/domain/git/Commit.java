package com.ryuqq.core.domain.git;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import com.ryuqq.core.storage.db.git.CommitCommand;

public class Commit {
	private Long id;
	private Long commitId;
	private String gitCommitId;
	private String author;
	private String commitMessage;
	private OffsetDateTime timestamp;
	private List<ChangedFile> changedFiles;

	public Commit(String gitCommitId, String author, String commitMessage,
				  OffsetDateTime timestamp,
				  List<ChangedFile> changedFiles) {
		this.gitCommitId = gitCommitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
		this.changedFiles = changedFiles;
	}


	public Commit(Long id, Long commitId, String gitCommitId, String author, String commitMessage,
				  OffsetDateTime timestamp,
				  List<ChangedFile> changedFiles) {
		this.id = id;
		this.commitId = commitId;
		this.gitCommitId = gitCommitId;
		this.author = author;
		this.commitMessage = commitMessage;
		this.timestamp = timestamp;
		this.changedFiles = changedFiles;
	}

	public CommitCommand toCommand(Long branchId){
		return new CommitCommand(null, branchId, gitCommitId, author, commitMessage, timestamp);
	}

	public Long getId() {
		return id;
	}

	public Long getCommitId() {
		return commitId;
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

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public List<ChangedFile> getChangedFiles() {
		return changedFiles;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		Commit commit = (Commit) object;
		return Objects.equals(id, commit.id)
			&& Objects.equals(commitId, commit.commitId)
			&& Objects.equals(gitCommitId, commit.gitCommitId)
			&& Objects.equals(author, commit.author)
			&& Objects.equals(commitMessage, commit.commitMessage)
			&& Objects.equals(timestamp, commit.timestamp)
			&& Objects.equals(changedFiles, commit.changedFiles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, commitId, gitCommitId, author, commitMessage, timestamp, changedFiles);
	}

	@Override
	public String toString() {
		return "Commit{"
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
			", author='"
			+ author
			+ '\''
			+
			", commitMessage='"
			+ commitMessage
			+ '\''
			+
			", timestamp="
			+ timestamp
			+
			", changedFiles="
			+ changedFiles
			+
			'}';
	}
}
