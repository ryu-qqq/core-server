package com.ryuqq.core.api.controller;



import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.ryuqq.core.api.payload.ApiResponse;
import com.ryuqq.core.api.payload.ErrorMessage;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.utils.TraceIdHolder;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
@RestController
public class GlobalExceptionController {

    private static final String ERROR_LOG_MSG_FORMAT = "CoreException : {}";
    private final Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception ex) {
		String traceId = TraceIdHolder.getTraceId();
		log.error("[TraceId: {}] Unhandled exception: {}", traceId, ex.getMessage(), ex);

		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body("Internal server error occurred.");
	}

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleValidationExceptions(IllegalArgumentException e) {
        String errorMsg = e.getMessage();
        log.warn(ERROR_LOG_MSG_FORMAT, errorMsg, e);

        ErrorType errorType = ErrorType.of(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(ApiResponse.error(new ErrorMessage(errorType, errorMsg)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String filedErrorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        String classObjectErrorMsg = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("Validation error occurred");

        StringBuilder sb = new StringBuilder();
        sb.append(filedErrorMsg);
        sb.append(classObjectErrorMsg);

        String errorMsg = sb.toString();

        log.warn(ERROR_LOG_MSG_FORMAT, errorMsg, e);
        ErrorType errorType = ErrorType.of(HttpStatus.BAD_REQUEST.value());

        if(errorMsg.isBlank()){
            errorMsg = e.getMessage();
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(new ErrorMessage(errorType, errorMsg)));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConstraintViolationException(ConstraintViolationException e) {
        String errorMsg = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        log.warn(ERROR_LOG_MSG_FORMAT, errorMsg, e);
        ErrorType errorType = ErrorType.of(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(new ErrorMessage(errorType, errorMsg)));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMsg;

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) e.getCause();

            if (invalidFormatException.getTargetType().isEnum()) {
                Class<?> enumClass = invalidFormatException.getTargetType();
                String invalidValue = invalidFormatException.getValue().toString();

                String validValues = Arrays.stream(enumClass.getEnumConstants())
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

                errorMsg = String.format(
                        "Invalid value '%s' for '%s'. Accepted values are: [%s].",
                        invalidValue, enumClass.getSimpleName(), validValues
                );
            } else {
                errorMsg = "Invalid format: " + e.getMessage();
            }
        } else {
            errorMsg = "Request could not be read. Please check the format of your JSON payload.";
        }

        log.warn("Error: {}", errorMsg, e);
        ErrorType errorType = ErrorType.of(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(new ErrorMessage(errorType, errorMsg)));
    }

}
