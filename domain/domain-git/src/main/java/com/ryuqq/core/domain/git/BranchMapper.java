package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.dto.BranchDto;

@Component
public class BranchMapper {

	public Branch toDomain(BranchDto branchDto){
		return BranchFactory.create(
			branchDto.getBranchId(),
			branchDto.getProjectId(),
			branchDto.getBaseBranchName(),
			branchDto.getBaseBranchName()
		);
	}

}
