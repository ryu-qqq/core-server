package com.ryuqq.core.external.git.response;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.annotation.JsonFormat;

public record GitHubCommitResponse(
	String sha,
	Commit commit,
	Committer committer
) {
	public static record Commit(
		String message
	) {}

	public static record Committer(
		String name,
		String email,
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		LocalDate data
	) {}

	public String getMessage(){
		return commit.message;
	}

	public String getAuthor(){
		return committer.name;
	}

	public OffsetDateTime getOffsetTimeDate(){
		return committer.data.atStartOfDay().atOffset(ZoneOffset.UTC);
	}

}
