package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.dto.BranchDto;

import org.springframework.stereotype.Component;

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
