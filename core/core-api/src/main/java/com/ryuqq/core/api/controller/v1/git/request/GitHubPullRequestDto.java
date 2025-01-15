package com.ryuqq.core.api.controller.v1.git.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubPullRequestDto(
	@JsonProperty("id")
	long projectId,
	@JsonProperty("name")
	String repositoryName,
	@JsonProperty("url")
	String repositoryUrl,
	String title,
	String body,
	Head head,
	Base base
) {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Head(
		@JsonProperty("ref")
		String sourceBranch
	){}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Base(
		@JsonProperty("ref")
		String targetBranch
	)
	{}


	public String getSourceBranch() {
		return head.sourceBranch;
	}

	public String getTargetBranch() {
		return base.targetBranch;
	}

}
