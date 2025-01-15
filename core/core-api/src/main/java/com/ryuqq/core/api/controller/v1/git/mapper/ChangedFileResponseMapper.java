package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.v1.git.response.ChangedFileResponseDto;
import com.ryuqq.core.domain.git.ChangedFile;
import com.ryuqq.core.enums.GitType;

import org.springframework.stereotype.Component;

@Component
public class ChangedFileResponseMapper {

	public ChangedFileResponseDto toResponseDto(GitType gitType, ChangedFile changedFile) {
		return new ChangedFileResponseDto(
			changedFile.getId(),
			changedFile.getClassName(),
			changedFile.getFilePath(),
			gitType,
			changedFile.getChangeType()
		);
	}
}
