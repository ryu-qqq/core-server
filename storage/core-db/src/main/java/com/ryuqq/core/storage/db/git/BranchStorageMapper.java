package com.ryuqq.core.storage.db.git;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.BranchCommand;

@Component
public class BranchStorageMapper {

	public BranchEntity toEntity(BranchCommand branchCommand){
		if(branchCommand.id() != null){
			return new BranchEntity(
				branchCommand.id(),
				branchCommand.projectId(),
				branchCommand.branchName(),
				branchCommand.baseBranchName()
			);
		}

		return new BranchEntity(
			branchCommand.projectId(),
			branchCommand.branchName(),
			branchCommand.baseBranchName()
		);
	}

}
