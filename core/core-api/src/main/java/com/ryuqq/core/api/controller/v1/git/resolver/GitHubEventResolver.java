package com.ryuqq.core.api.controller.v1.git.resolver;

import jakarta.servlet.http.HttpServletRequest;

import com.ryuqq.core.api.controller.v1.git.request.GitHubWebhookRequestDto;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class GitHubEventResolver implements HandlerMethodArgumentResolver {


	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return GitHubWebhookRequestDto.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest,
								  WebDataBinderFactory binderFactory) throws Exception {

		String eventType = webRequest.getHeader("X-GitHub-Event");
		if (eventType == null) {
			throw new IllegalArgumentException("Missing required header: X-GitHub-Event");
		}


		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if (request == null) {
			throw new IllegalStateException("Unable to retrieve HttpServletRequest");
		}

		String payload = request.getReader().lines().reduce("", String::concat);

		return GitHubEventFactory.getEventInfo(eventType, payload);
	}



}
