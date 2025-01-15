package com.ryuqq.core.api.controller.v1.git.request;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitLabMergeEventWebhookRequestDto(
	String objectKind,
	String ref,
	@JsonProperty("project_id")
	long projectId,
	@JsonProperty("user_name")
	String userName,
	List<Commit> commits,
	Repository repository,
	List<Label> labels
) {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Commit(
		String id,
		String message,
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
		OffsetDateTime timestamp,
		Author author,
		List<String> added,
		List<String> modified,
		List<String> removed
	){}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Author(
		String name,
		String email
	){}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Repository(
		String name,
		String url,
		String description,
		String homepage
	){}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Label(
		long id,
		String name
	){}

	public List<String> getLabelNames(){
		return labels.stream()
			.map(label -> label.name)
			.toList();
	}

}
