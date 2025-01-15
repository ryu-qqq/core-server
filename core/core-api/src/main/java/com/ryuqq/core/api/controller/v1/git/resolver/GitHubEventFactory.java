package com.ryuqq.core.api.controller.v1.git.resolver;

import com.ryuqq.core.api.controller.v1.git.request.GitHubCreateEventRequestDto;
import com.ryuqq.core.api.controller.v1.git.request.GitHubPullRequestEventInfo;
import com.ryuqq.core.api.controller.v1.git.request.GitHubPushEventInfo;
import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;
import com.ryuqq.core.utils.JsonUtils;

public class GitHubEventFactory {

	public static GitHubWebhookRequestDto getEventInfo(String eventType, String payload) throws Exception {
		return switch (eventType) {
			case "push" -> JsonUtils.fromJson(payload, GitHubPushEventInfo.class);
			case "create" -> JsonUtils.fromJson(payload, GitHubCreateEventRequestDto.class);
			case "pull_request" -> JsonUtils.fromJson(payload, GitHubPullRequestEventInfo.class);
			default -> throw new IllegalArgumentException("Unsupported GitHub event type: "
				+ eventType);
		};
	}

}
