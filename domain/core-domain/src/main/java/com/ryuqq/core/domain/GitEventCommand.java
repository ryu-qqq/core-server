package com.ryuqq.core.domain;

import java.util.List;

public record GitEventCommand(
	BranchCommand branchCommand,
	List<ChangedFileCommand> changedFileCommands
) {


}
