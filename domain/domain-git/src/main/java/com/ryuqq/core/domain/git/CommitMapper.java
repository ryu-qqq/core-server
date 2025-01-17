package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.dto.ChangedFileDto;
import com.ryuqq.core.storage.db.git.dto.CommitDto;

@Component
public class CommitMapper {

	private final ChangedFileMapper changedFileMapper;

	public CommitMapper(ChangedFileMapper changedFileMapper) {
		this.changedFileMapper = changedFileMapper;
	}

	public Commit toDomain(CommitDto commitDto) {
		return new Commit(
			commitDto.getId(),
			commitDto.getBranchId(),
			commitDto.getGitCommitId(),
			commitDto.getCommitMessage(),
			commitDto.getCommitMessage(),
			commitDto.getTimestamp(),
			toChangedFiles(commitDto.getChangedFiles())
		);
	}

	private List<ChangedFile> toChangedFiles(List<ChangedFileDto> changedFiles) {
		return changedFiles.stream().map(changedFileMapper::toDomain).toList();
	}
}
