package com.ryuqq.core.api.controller.v1.git.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubPushEventRequestDto(
	@JsonProperty("ref")
	String branchName,
	GitHubRepository repository,
	List<GitHubCommit> commits

) implements GitHubWebhookRequestDto{

	public long getGitProjectId(){
		return repository.projectId();
	}

	public String getBranchName() {
		return branchName.replaceAll("refs/heads/", "");
	}


}
