package com.ryuqq.core.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.payload.ApiResponse;


@RestController
@RequestMapping("")
public class HealthCheckController {

	@GetMapping("/health")
	public ResponseEntity<ApiResponse<Object>> fetchHealth(){
		return ResponseEntity.ok(ApiResponse.success(null));
	}

}
