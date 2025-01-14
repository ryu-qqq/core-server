package com.ryuqq.core.external.git;

import com.ryuqq.core.external.GlobalFeignConfig;
import com.ryuqq.core.external.git.response.GitHubCommitResponse;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
	name = "githubApiClient",
	url = "https://api.github.com",
	configuration = GlobalFeignConfig.class

)
public interface GitHubFeignClient {

	@GetMapping("/repos/{fullName}/pulls/{pullNumber}/files")
	List<GitHubCommitResponse> getCommitInfos(@PathVariable("fullName") String fullName,
									   @PathVariable("pullNumber") int pullNumber);


}
