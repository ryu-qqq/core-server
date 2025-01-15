package com.ryuqq.core.api.controller.v1.git.request;

import com.ryuqq.core.enums.MergeStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubPullRequestEventDto(
	String action,
	@JsonProperty("number")
	long gitPullId,
	@JsonProperty("pull_request")
	GitHubPullRequestDto pullRequest,
	GitHubRepository repository
) implements GitHubWebhookRequestDto
{
	public long getGitProjectId(){
		return pullRequest.projectId();
	}

	public String getSourceBranch(){
		return pullRequest().getSourceBranch();
	}

	public String getTargetBranch(){
		return pullRequest().getTargetBranch();
	}

	public String getTitle(){
		return pullRequest().title();
	}

	public String getDescription(){
		return pullRequest.body();
	}

	public MergeStatus getMergeStatus(){
		return MergeStatus.of(action.toUpperCase());
	}

	public String getFullName(){
		return repository.fullName();
	}

}
