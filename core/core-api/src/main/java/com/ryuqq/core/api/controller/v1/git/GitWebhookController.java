package com.ryuqq.core.api.controller.v1.git;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.git.request.GitPushEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.response.GitPushEventResponseDto;
import com.ryuqq.core.api.controller.v1.git.service.GitWebhookHandler;
import com.ryuqq.core.api.payload.ApiResponse;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class GitWebhookController {

	private final GitWebhookHandler gitWebhookHandler;

	public GitWebhookController(GitWebhookHandler gitWebhookHandler) {
		this.gitWebhookHandler = gitWebhookHandler;
	}

	@PostMapping("/git/webhook")
	public ResponseEntity<ApiResponse<GitPushEventResponseDto>> handleGitWebHook(
		@RequestBody GitPushEventRequestDto gitLabPushEventRequestDto) {
		return ResponseEntity.ok(ApiResponse.success(gitWebhookHandler.handle(gitLabPushEventRequestDto)));
	}



}
