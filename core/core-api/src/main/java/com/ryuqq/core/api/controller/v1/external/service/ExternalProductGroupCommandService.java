package com.ryuqq.core.api.controller.v1.external.service;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.ExternalProductGroupRegister;

@Service
public class ExternalProductGroupCommandService {

	private final ExternalProductGroupRegister externalProductGroupRegister;

	public ExternalProductGroupCommandService(ExternalProductGroupRegister externalProductGroupRegister) {
		this.externalProductGroupRegister = externalProductGroupRegister;
	}
}
