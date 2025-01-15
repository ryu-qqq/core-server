package com.ryuqq.core.api.controller.v1.git.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubMergeEventWebhookRequestDto(
	String action,
	@JsonProperty("number")
	int pullRequestNumber,
	@JsonProperty("pull_request")
	PullRequest pullRequest,
	Repository repository,
	Sender sender
) {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record PullRequest(
		String url,
		@JsonProperty("html_url")
		String htmlUrl,
		@JsonProperty("diff_url")
		String diffUrl,
		@JsonProperty("patch_url")
		String patchUrl,
		@JsonProperty("commits_url")
		String commitUrl,
		String title,
		User user,
		@JsonProperty("created_at")
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
		String createdAt,
		@JsonProperty("updated_at")
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
		String updatedAt,
		@JsonProperty("head")
		Branch head,
		@JsonProperty("base")
		Branch base,
		List<Label> labels
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record User(
		String login,
		long id,
		@JsonProperty("html_url")
		String htmlUrl
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Branch(
		String ref,
		String sha,
		User user
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Repository(
		long id,
		String name,
		@JsonProperty("full_name")
		String fullName,
		@JsonProperty("html_url")
		String htmlUrl,
		Owner owner
	) {

		public String getOwnerLogin(){
			return owner.login;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Label(
		long id,
		String name,
		String description
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Sender(
		String login,
		long id,
		@JsonProperty("html_url")
		String htmlUrl
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Owner(
		String login,
		long id
	){}

	public String getBranchName(){
		return pullRequest.base.ref;
	}

	public String getFullName(){
		return repository.fullName;
	}

	public String getOwner(){
		return repository.owner.login;
	}

	public List<String> getLabelNames(){
		return pullRequest.labels.stream()
			.map(Label::name)
			.toList();
	}
}
