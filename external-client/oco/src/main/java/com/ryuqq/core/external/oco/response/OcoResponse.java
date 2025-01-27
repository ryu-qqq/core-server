package com.ryuqq.core.external.oco.response;

import org.springframework.http.HttpStatus;

public record OcoResponse<T>(
            T apiResult,
            OcoPage pageSet,
            OcoResponseStatus responseStatus
) {

	public HttpStatus getHttpStatus(){
		return HttpStatus.valueOf(responseStatus().statusCode());
	}

	public String getMessage(){
		return responseStatus.returnMessage();
	}

}
