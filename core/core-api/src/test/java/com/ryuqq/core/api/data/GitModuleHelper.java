package com.ryuqq.core.api.data;

import java.time.LocalDateTime;
import java.util.List;

import com.ryuqq.core.api.controller.v1.git.request.GitPushEventRequestDto;

public class GitModuleHelper {

	public static GitPushEventRequestDto toGitPushEventRequestDto(){
		return new GitPushEventRequestDto(
			"push",
			"refs/heads/main",
			12345L,
			"test-user",
			List.of(
				new GitPushEventRequestDto.Commit(
					"commit-id",
					"Initial commit",
					LocalDateTime.now(),
					List.of("src/main/java/NewFile1.java"),
					List.of("src/main/java/NewFile2.java"),
					List.of("src/main/java/NewFile3.java")
				)
			),
			new GitPushEventRequestDto.Repository(
				"test-repo",
				"http://github.com/test-repo",
				"Test repository",
				"http://github.com/test-repo/homepage"
			)
		);
	}
}
