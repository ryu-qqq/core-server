package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ExternalProductGroupContextFinder {

	private final ExternalProductGroupAssembler externalProductGroupAssembler;

	public ExternalProductGroupContextFinder(ExternalProductGroupAssembler externalProductGroupAssembler) {
		this.externalProductGroupAssembler = externalProductGroupAssembler;
	}

	public List<ExternalProductGroupContext> fetchByProductGroupId(long productGroupId){
		return externalProductGroupAssembler.assemble(productGroupId);
	}

	public List<ExternalProductGroupContext> fetchByProductGroupIds(List<Long> productGroupIds){
		return externalProductGroupAssembler.assemble(productGroupIds);
	}

}
