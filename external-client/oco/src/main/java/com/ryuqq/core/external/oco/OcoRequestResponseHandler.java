package com.ryuqq.core.external.oco;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.external.ExternalSiteException;
import com.ryuqq.core.external.oco.response.OcoResponse;

@Component
public class OcoRequestResponseHandler {

	private static final String OCO_SERVER_ERROR_MSG = "Failed communication data";
	private static final String SUCCESS_MESSAGE = "success";

	public <T> T handleResponse(OcoResponse<T> response) {
		if (response == null) {
			throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  OCO_SERVER_ERROR_MSG);
		}

		if (response.getHttpStatus().is2xxSuccessful()) {

			if(response.responseStatus().statusCode() != 200){
				throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  response.responseStatus().returnMessage());
			}

			T t = response.apiResult();
			if (t != null) {
				String message = response.getMessage();

				if (!SUCCESS_MESSAGE.equals(message)) {
					throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  message);
				}

				return t;
			}
		}

		if(response.responseStatus() != null){
			throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  response.responseStatus().returnMessage());
		}

		throw new IllegalStateException(OCO_SERVER_ERROR_MSG);
	}

}
