package com.ryuqq.core.api.controller.v1.git.resolver;

import com.ryuqq.core.api.controller.v1.git.request.GitHubBranchCreateEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubPullRequestEventDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubPushEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;
import com.ryuqq.core.utils.JsonUtils;

public class GitHubEventFactory {

	public static GitHubWebhookRequestDto getEventInfo(String eventType, String payload) {
		return switch (eventType) {
			case "push" -> JsonUtils.fromJson(payload, GitHubPushEventRequestDto.class);
			case "create" -> JsonUtils.fromJson(payload, GitHubBranchCreateEventRequestDto.class);
			case "pull_request" -> JsonUtils.fromJson(payload, GitHubPullRequestEventDto.class);
			default -> throw new IllegalArgumentException("Unsupported GitHub event type: "
				+ eventType);
		};
	}

}
