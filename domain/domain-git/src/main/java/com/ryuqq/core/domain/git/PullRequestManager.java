package com.ryuqq.core.domain.git;

import org.springframework.stereotype.Component;

@Component
public class PullRequestManager {

	private final PullRequestRegister pullRequestRegister;

	public PullRequestManager(PullRequestRegister pullRequestRegister) {
		this.pullRequestRegister = pullRequestRegister;
	}




}
