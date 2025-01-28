package com.ryuqq.core.external.oco;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.external.ExternalSiteException;
import com.ryuqq.core.external.oco.response.OcoResponse;

@Component
public class OcoRequestResponseHandler {

	private static final String OCO_AUTH_ERROR_MSG = "token 없음";
	private static final String OCO_DATA_NULL_MSG = "Response body is null";
	private static final String OCO_SERVER_ERROR_MSG = "Failed communication data";
	private static final String SUCCESS_MESSAGE = "Success";

	public <T> T handleResponse(OcoResponse<T> response) {
		if (response == null) {
			throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  OCO_SERVER_ERROR_MSG);
		}

		if (response.getHttpStatus().is2xxSuccessful()) {
			T t = response.apiResult();
			if (t != null) {
				String message = response.getMessage();
				if (OCO_AUTH_ERROR_MSG.equals(message)) {
					throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  "Unauthorized: Invalid token");
				}
				if (!SUCCESS_MESSAGE.equals(message)) {
					throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  OCO_SERVER_ERROR_MSG);
				}
				return t;
			} else {
				throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  OCO_DATA_NULL_MSG);
			}
		}

		throw new IllegalStateException(OCO_SERVER_ERROR_MSG);
	}



}
