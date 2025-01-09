package com.ryuqq.core.api.payload;

import com.ryuqq.core.enums.ErrorType;

import org.springframework.http.HttpStatus;


public class ErrorMessage {
    private final String code;
    private final String message;
    private final HttpStatus status;


    public ErrorMessage(ErrorType errorType) {
        this.code = errorType.getCode().name();
        this.message = errorType.getMessage();
        this.status = HttpStatus.valueOf(errorType.getStatus());
    }

    public ErrorMessage(ErrorType errorType, String message) {
        this.code = errorType.getCode().name();
        this.message = String.format("%s, Detail Message : %s",errorType.getMessage(), message);
        this.status = HttpStatus.valueOf(errorType.getStatus());
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }


    public ErrorResponse toErrorResponse(){
        return new ErrorResponse(this);
    }
}
