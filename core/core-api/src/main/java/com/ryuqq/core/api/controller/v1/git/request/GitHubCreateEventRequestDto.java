package com.ryuqq.core.api.controller.v1.git.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubCreateEventRequestDto(
	@JsonProperty("ref")
	String branchName,
	@JsonProperty("master_branch")
	String baseBranchName,
	GitHubRepository repository
) implements GitHubWebhookRequestDto{

	public long getProjectId(){
		return repository().projectId();
	}

	public String getBranchName(){
		return branchName;
	}

	public String baseBranchName(){
		return baseBranchName;
	}

}
