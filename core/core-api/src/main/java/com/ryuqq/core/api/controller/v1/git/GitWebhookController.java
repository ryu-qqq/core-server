package com.ryuqq.core.api.controller.v1.git;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.git.request.GitHubMergeEventWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitLabMergeEventWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitPushEventResponseDto;
import com.ryuqq.core.api.controller.v1.git.service.GitLabWebhookHandler;
import com.ryuqq.core.api.controller.v1.git.service.GitWebhookHandler;
import com.ryuqq.core.api.controller.v1.git.service.GitWebhookHandlerProvider;
import com.ryuqq.core.api.payload.ApiResponse;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class GitWebhookController {

	private final GitWebhookHandlerProvider gitWebhookHandlerProvider;

	public GitWebhookController(GitWebhookHandlerProvider gitWebhookHandlerProvider) {
		this.gitWebhookHandlerProvider = gitWebhookHandlerProvider;
	}

	@PostMapping("/webhook/gitlab")
	public ResponseEntity<ApiResponse<GitPushEventResponseDto>> handleGitLabWebhook(@RequestBody GitLabMergeEventWebhookRequestDto requestDto) {
		GitWebhookHandler<GitLabMergeEventWebhookRequestDto> handler = gitWebhookHandlerProvider.getHandler(GitLabMergeEventWebhookRequestDto.class);
		return ResponseEntity.ok(ApiResponse.success(handler.handle(requestDto)));
	}

	@PostMapping("/webhook/github")
	public ResponseEntity<ApiResponse<GitPushEventResponseDto>> handleGitWebHook(@RequestBody GitHubMergeEventWebhookRequestDto requestDto) {
		GitWebhookHandler<GitHubMergeEventWebhookRequestDto> handler = gitWebhookHandlerProvider.getHandler(GitHubMergeEventWebhookRequestDto.class);
		return ResponseEntity.ok(ApiResponse.success(handler.handle(requestDto)));
	}


}
