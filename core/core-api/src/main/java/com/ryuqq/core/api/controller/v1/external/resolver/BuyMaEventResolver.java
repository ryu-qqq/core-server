package com.ryuqq.core.api.controller.v1.external.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ryuqq.core.api.controller.filter.RequestWrapper;
import com.ryuqq.core.api.controller.v1.external.request.BuyMaEventRequestDto;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class BuyMaEventResolver implements HandlerMethodArgumentResolver  {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return BuyMaEventRequestDto.class.isAssignableFrom(parameter.getParameterType());

	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		String eventType = webRequest.getHeader("X-Buyma-Event");
		if (eventType == null) {
			throw new IllegalArgumentException("Missing required header: \"X-Buyma-Event\"");
		}

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if (request == null) {
			throw new IllegalStateException("Unable to retrieve HttpServletRequest");
		}

		if (!(request instanceof RequestWrapper)) {
			request = new RequestWrapper(request);
		}

		String payload = new String(((RequestWrapper) request).getContentAsByteArray(), request.getCharacterEncoding());

		return BuyMaEventFactory.getEventInfo(eventType, payload);
	}

}
