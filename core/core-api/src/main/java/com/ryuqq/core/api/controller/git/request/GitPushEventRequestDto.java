package com.ryuqq.core.api.controller.git.request;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitPushEventRequestDto(
	String objectKind,
	String ref,
	@JsonProperty("project_id")
	long projectId,
	@JsonProperty("user_name")
	String userName,
	List<Commit> commits,
	Repository repository
) {
	public record Commit(
		String id,
		String message,
		LocalDateTime timestamp,
		List<String> added,
		List<String> modified,
		List<String> removed
	){}

	public record Repository(
		String name,
		String url,
		String description,
		String homepage
	){}

}
