package com.ryuqq.core.api.controller.v1.git;

import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.service.GitHubWebhookHandler;
import com.ryuqq.core.api.controller.v1.git.service.GitHubWebhookHandlerProvider;
import com.ryuqq.core.api.payload.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class GitWebhookController {

	private final GitHubWebhookHandlerProvider gitHubWebhookHandlerProvider;

	public GitWebhookController(GitHubWebhookHandlerProvider gitHubWebhookHandlerProvider) {
		this.gitHubWebhookHandlerProvider = gitHubWebhookHandlerProvider;
	}

	@PostMapping("/webhook/github")
	public <T extends GitHubWebhookRequestDto> ResponseEntity<ApiResponse<?>> handleGitWebHook(T requestDto) {
		@SuppressWarnings("unchecked")
		GitHubWebhookHandler<T, ?> handler = (GitHubWebhookHandler<T, ?>) gitHubWebhookHandlerProvider.getHandler(requestDto.getClass());
		return ResponseEntity.ok(ApiResponse.success(handler.handle(requestDto)));
	}


}
