package com.ryuqq.core.external.git;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ryuqq.core.external.git.response.GitHubCommitResponse;

@FeignClient(
	name = "githubApiClient",
	url = "https://api.github.com"
)
public interface GitHubFeignClient {

	@GetMapping("/repos/{fullName}/pulls/{pullNumber}/files")
	List<GitHubCommitResponse> getCommitInfos(@PathVariable("fullName") String fullName, @PathVariable("pullNumber") long pullNumber);

}
