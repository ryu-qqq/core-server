package com.ryuqq.core.api.controller.git;

import com.ryuqq.core.api.controller.git.request.GitPushEventRequestDto;
import com.ryuqq.core.api.controller.git.response.GitPushEventResponseDto;
import com.ryuqq.core.api.payload.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class GitWebhookController {


	@PostMapping("/git/webhook")
	public void handleGitWebHook(@RequestBody GitPushEventRequestDto gitLabPushEventRequestDto) {

	}



}
