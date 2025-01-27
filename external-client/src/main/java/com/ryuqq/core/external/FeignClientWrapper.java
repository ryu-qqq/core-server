package com.ryuqq.core.external;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import feign.FeignException;

@Component
public class FeignClientWrapper {

	public <T> T executeFeignCall(Supplier<ResponseEntity<T>> feignCall) {
		try {
			ResponseEntity<T> response = feignCall.get();
			return response.getBody();
		} catch (FeignException e) {
			throw new ExternalSiteException(e.getMessage());
		}
	}

}
