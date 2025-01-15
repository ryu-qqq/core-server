package com.ryuqq.core.api.controller.v1.git.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubRepository(
	@JsonProperty("id")
	long projectId,
	@JsonProperty("name")
	String repositoryName,
	@JsonProperty("url")
	String repositoryUrl

) {
}
