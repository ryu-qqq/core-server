package com.ryuqq.core.domain.git;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Commit {
	private Long id;
	private Long branchId;
	private String gitCommitId;
	private String author;
	private String commitMessage;
	private LocalDateTime timestamp;
	private List<ChangedFile> changedFiles;

	private Commit(Long id, Long branchId, String gitCommitId, String author, String commitMessage,
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

	public static Commit create(String gitCommitId, String author, String commitMessage, LocalDateTime timestamp,
								List<ChangedFile> changedFiles){

		return new Commit(null, null, gitCommitId, author, commitMessage, timestamp, changedFiles);
	}

	public static Commit create(Long id, long branchId, String gitCommitId, String author, String commitMessage, LocalDateTime timestamp, List<ChangedFile> changedFiles) {
		return new Commit(null, branchId, gitCommitId, author, commitMessage, timestamp, changedFiles);
	}

	public CommitCommand toCommand(long branchId){
		if(id != null){
			return new UpdateCommitCommand(id, branchId, gitCommitId, author, commitMessage, timestamp);
		}
		return new CreateCommitCommand(branchId, gitCommitId, author, commitMessage, timestamp);
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

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		Commit commit = (Commit) object;
		return branchId
			== commit.branchId
			&& Objects.equals(id, commit.id)
			&& Objects.equals(gitCommitId, commit.gitCommitId)
			&& Objects.equals(author, commit.author)
			&& Objects.equals(commitMessage, commit.commitMessage)
			&& Objects.equals(timestamp, commit.timestamp)
			&& Objects.equals(changedFiles, commit.changedFiles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, branchId, gitCommitId, author, commitMessage, timestamp, changedFiles);
	}

	@Override
	public String toString() {
		return "Commit{"
			+
			"id="
			+ id
			+
			", branchId="
			+ branchId
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
