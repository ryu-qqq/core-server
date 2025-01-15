package com.ryuqq.core.api.controller.v1.git.request;

import java.time.LocalDateTime;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubCommit(
	@JsonProperty("id")
	String gitCommitId,
	@JsonProperty("message")
	String commitMessage,
	@JsonProperty("timestamp")
	String createAt,
	GitHubAuthor author,
	List<String> added,
	List<String> removed,
	List<String> modified
) {

	public String getAuthor(){
		return author.name();
	}

	public LocalDateTime getCreateAt() {
		ZonedDateTime zonedDateTime = ZonedDateTime.parse(createAt);
		return zonedDateTime.toLocalDateTime();
	}

}
