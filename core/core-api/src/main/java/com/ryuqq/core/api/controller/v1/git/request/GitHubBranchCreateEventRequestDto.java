package com.ryuqq.core.api.controller.v1.git.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubBranchCreateEventRequestDto(
	@JsonProperty("ref")
	String branchName,
	@JsonProperty("master_branch")
	String baseBranchName,
	GitHubRepository repository
) implements GitHubWebhookRequestDto{

	public long getGitProjectId(){
		return repository().projectId();
	}

	public String getBranchName(){
		return branchName;
	}

	public String baseBranchName(){
		return baseBranchName;
	}

}
