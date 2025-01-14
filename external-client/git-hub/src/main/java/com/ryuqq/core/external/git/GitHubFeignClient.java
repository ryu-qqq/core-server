package com.ryuqq.core.external.git;

import com.ryuqq.core.external.git.response.GitHubCommitResponse;
import com.ryuqq.core.external.git.response.GitHubPullRequestFileResponse;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
	name = "githubApiClient",
	url = "https://api.github.com",
	configuration = GitHubFeignClientConfig.class

)
public interface GitHubFeignClient {

	@GetMapping("/{commitUrl}")
	GitHubCommitResponse getCommitInfo(@PathVariable("commitUrl") String commitUrl);

	@GetMapping("/repos/{owner}/{repo}/pulls/{pullNumber}/files")
	List<GitHubPullRequestFileResponse> getPullRequestFiles(
		@PathVariable("owner") String owner,
		@PathVariable("repo") String repo,
		@PathVariable("pullNumber") int pullNumber
	);

}
