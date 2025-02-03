package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.BranchFactory;
import com.ryuqq.core.storage.db.git.dto.BranchDto;

@Component
public class BranchDomainMapper {

	public Branch toDomain(BranchDto branchDto){
		return BranchFactory.create(
			branchDto.getBranchId(),
			branchDto.getProjectId(),
			branchDto.getBaseBranchName(),
			branchDto.getBaseBranchName()
		);
	}

}
