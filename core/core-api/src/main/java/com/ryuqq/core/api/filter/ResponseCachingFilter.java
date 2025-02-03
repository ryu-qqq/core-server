package com.ryuqq.core.api.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ResponseCachingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

		try {
			filterChain.doFilter(request, wrappedResponse);
		} finally {
			wrappedResponse.copyBodyToResponse();
		}

	}

}
