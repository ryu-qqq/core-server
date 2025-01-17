package com.ryuqq.core.api.controller.v1.git;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.PullRequestFilterDto;
import com.ryuqq.core.api.controller.v1.git.response.PullRequestChangedFileResponseDto;
import com.ryuqq.core.api.controller.v1.git.response.PullRequestSummaryResponseDto;
import com.ryuqq.core.api.controller.v1.git.response.PullRequestUpdatedResponseDto;
import com.ryuqq.core.api.controller.v1.git.service.GitHubWebhookHandler;
import com.ryuqq.core.api.controller.v1.git.service.GitHubWebhookHandlerProvider;
import com.ryuqq.core.api.controller.v1.git.service.PullRequestChangedFileFetchService;
import com.ryuqq.core.api.controller.v1.git.service.PullRequestCommandService;
import com.ryuqq.core.api.controller.v1.git.service.PullRequestContextFetchService;
import com.ryuqq.core.api.payload.ApiResponse;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.enums.ReviewStatus;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class GitWebhookController {

	private final GitHubWebhookHandlerProvider gitHubWebhookHandlerProvider;
	private final PullRequestContextFetchService pullRequestContextFetchService;
	private final PullRequestChangedFileFetchService pullRequestChangedFileFetchService;
	private final PullRequestCommandService pullRequestCommandService;

	public GitWebhookController(GitHubWebhookHandlerProvider gitHubWebhookHandlerProvider,
								PullRequestContextFetchService pullRequestContextFetchService,
								PullRequestChangedFileFetchService pullRequestChangedFileFetchService,
								PullRequestCommandService pullRequestCommandService) {
		this.gitHubWebhookHandlerProvider = gitHubWebhookHandlerProvider;
		this.pullRequestContextFetchService = pullRequestContextFetchService;
		this.pullRequestChangedFileFetchService = pullRequestChangedFileFetchService;
		this.pullRequestCommandService = pullRequestCommandService;
	}

	@PostMapping("/webhook/github")
	public <T extends GitHubWebhookRequestDto> ResponseEntity<ApiResponse<?>> handleGitWebHook(T requestDto) {
		@SuppressWarnings("unchecked")
		GitHubWebhookHandler<T, ?> handler = (GitHubWebhookHandler<T, ?>) gitHubWebhookHandlerProvider.getHandler(requestDto.getClass());
		return ResponseEntity.ok(ApiResponse.success(handler.handle(requestDto)));
	}

	@GetMapping("/pull-requests")
	public ResponseEntity<ApiResponse<Slice<PullRequestSummaryResponseDto>>> fetchPullRequestContextResponse(@ModelAttribute PullRequestFilterDto filterDto){
		return ResponseEntity.ok(ApiResponse.success(pullRequestContextFetchService.fetchByFilter(filterDto)));
	}

	@GetMapping("/pull-requests/file/{pullRequestId}")
	public ResponseEntity<ApiResponse<List<PullRequestChangedFileResponseDto>>> fetchPullRequestContextResponse(@PathVariable("pullRequestId") long pullRequestId){
		return ResponseEntity.ok(ApiResponse.success(pullRequestChangedFileFetchService.fetchPullRequestChangedFilesById(pullRequestId)));
	}

	@PatchMapping("/pull-requests/{pullRequestId}")
	public ResponseEntity<ApiResponse<PullRequestUpdatedResponseDto>> updatePullRequestStatus(@PathVariable("pullRequestId") long pullRequestId, @RequestParam("ReviewStatus") ReviewStatus reviewStatuses){
		return ResponseEntity.ok(ApiResponse.success(pullRequestCommandService.updateReviewStatusById(pullRequestId, reviewStatuses)));
	}

}
