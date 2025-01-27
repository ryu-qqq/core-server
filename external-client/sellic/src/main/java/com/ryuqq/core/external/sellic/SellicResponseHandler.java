package com.ryuqq.core.external.sellic;

import org.springframework.stereotype.Component;

import com.ryuqq.core.external.ExternalSiteException;
import com.ryuqq.core.external.sellic.response.SellicResponse;

@Component
public class SellicResponseHandler {

	private static final String SELLIC_SERVER_ERROR_MSG = "Failed communication data";
	private static final String SELLIC_RESULT_ERROR_MSG = "eroor";


	public SellicResponse handleResponse(SellicResponse response) {
		if (response == null) {
			throw new ExternalSiteException(SELLIC_SERVER_ERROR_MSG);
		}

		if(response.result().equals(SELLIC_RESULT_ERROR_MSG)){
			throw new ExternalSiteException(response.message());
		}

		return response;
	}

}
