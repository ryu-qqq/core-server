package com.ryuqq.core.api.controller;

import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.api.payload.ApiResponse;
import com.ryuqq.core.api.payload.ErrorMessage;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.utils.TraceIdHolder;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RestController
public class GlobalExceptionController {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
		String traceId = TraceIdHolder.getTraceId();

		List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream()
			.map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
			.collect(Collectors.toList());

		log.error("Validation error: TraceId={} Errors={}", traceId, validationErrors);

		ErrorMessage errorMessage = new ErrorMessage(ErrorType.BAD_REQUEST_ERROR, validationErrors.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(errorMessage));
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ApiResponse<?>> handleBindException(BindException ex) {
		String traceId = TraceIdHolder.getTraceId();

		List<String> errors = ex.getFieldErrors().stream()
			.map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
			.collect(Collectors.toList());

		log.error("Binding error: TraceId={} Errors={}", traceId, errors);

		ErrorMessage errorMessage = new ErrorMessage(ErrorType.BAD_REQUEST_ERROR, errors.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(errorMessage));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleUnexpectedException(Exception ex) {
		String traceId = TraceIdHolder.getTraceId();

		log.error("Unexpected error: TraceId={} Message={}", traceId, ex.getMessage(), ex);

		ErrorMessage errorMessage = new ErrorMessage(ErrorType.UNEXPECTED_ERROR, "An unexpected error occurred.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(errorMessage));
	}

	@ExceptionHandler(CoreException.class)
	public ResponseEntity<ApiResponse<?>> handleCoreException(CoreException ex) {
		String traceId = TraceIdHolder.getTraceId();

		log.error("CoreException: TraceId={} Message={}", traceId, ex.getMessage(), ex);

		ErrorMessage errorMessage = new ErrorMessage(ex.getErrorType(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.valueOf(ex.getErrorType().getStatus())).body(ApiResponse.error(errorMessage));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex) {
		String traceId = TraceIdHolder.getTraceId();

		log.error("RuntimeException: TraceId={} Message={}", traceId, ex.getMessage(), ex);

		ErrorMessage errorMessage = new ErrorMessage(ErrorType.UNEXPECTED_ERROR, "An unexpected runtime error occurred.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(errorMessage));
	}


}
