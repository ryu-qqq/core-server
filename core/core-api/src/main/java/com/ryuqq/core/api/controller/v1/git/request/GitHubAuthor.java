package com.ryuqq.core.api.controller.v1.git.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubAuthor(
	String name,
	String email
) {}
