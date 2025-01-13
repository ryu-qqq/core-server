package com.ryuqq.core.domain;

import java.util.List;
import java.util.Objects;

public class GitEvent {
	private Branch branch;
	private List<ChangedFile> changedFiles;

	public GitEvent(
		Branch branch,
		List<ChangedFile> changedFiles
	) {
		this.branch = branch;
		this.changedFiles = changedFiles;
	}

	public Branch getBranch() {
		return branch;
	}

	public List<ChangedFile> getChangedFiles() {
		return changedFiles;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj
			== this) return true;
		if (obj
			== null
			|| obj.getClass()
			!= this.getClass()) return false;
		var that = (GitEvent) obj;
		return Objects.equals(this.branch, that.branch)
			&&
			Objects.equals(this.changedFiles, that.changedFiles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(branch, changedFiles);
	}

	@Override
	public String toString() {
		return "GitEvent["
			+
			"branch="
			+ branch
			+ ", "
			+
			"changedFiles="
			+ changedFiles
			+ ']';
	}

}
