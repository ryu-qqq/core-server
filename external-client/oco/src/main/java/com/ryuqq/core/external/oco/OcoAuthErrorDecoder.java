package com.ryuqq.core.external.oco;

import org.springframework.http.HttpStatus;

import com.ryuqq.core.external.ExternalSiteException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class OcoAuthErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() == HttpStatus.BAD_REQUEST.value()) {
			return new ExternalSiteException("Invalid credentials or bad request for authentication.");
		} else if (response.status() == HttpStatus.UNAUTHORIZED.value()) {
			return new ExternalSiteException("Unauthorized access while fetching token.");
		}

		return new Default().decode(methodKey, response);
	}

}
