package com.ryuqq.core.domain.git;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class BranchFinder {


	public Optional<Branch> fetchByProjectIdAndBranchName(long projectId, String branchName){
		return Optional.empty();
	}


}
