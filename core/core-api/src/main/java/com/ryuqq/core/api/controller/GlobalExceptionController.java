package com.ryuqq.core.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.api.payload.ApiResponse;
import com.ryuqq.core.api.payload.ErrorMessage;
import com.ryuqq.core.enums.ErrorType;

@RestControllerAdvice
@RestController
public class GlobalExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {

		List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream()
			.map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
			.toList();

		ErrorMessage errorMessage = new ErrorMessage(ErrorType.BAD_REQUEST_ERROR, validationErrors.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(errorMessage));
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ApiResponse<?>> handleBindException(BindException ex) {

		List<String> errors = ex.getFieldErrors().stream()
			.map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
			.toList();

		ErrorMessage errorMessage = new ErrorMessage(ErrorType.BAD_REQUEST_ERROR, errors.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(errorMessage));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleUnexpectedException(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorType.UNEXPECTED_ERROR, "An unexpected error occurred.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(errorMessage));
	}

	@ExceptionHandler(CoreException.class)
	public ResponseEntity<ApiResponse<?>> handleApplicationException(CoreException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getErrorType(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(errorMessage));
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleNoHandlerFoundException(NoHandlerFoundException ex) {

		ErrorMessage errorMessage = new ErrorMessage(
			ErrorType.NOT_FOUND_ERROR,
			String.format("No handler found for %s %s", ex.getHttpMethod(), ex.getRequestURL())
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(errorMessage));
	}
}
