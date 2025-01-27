package com.ryuqq.core.storage.db.git;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.ChangedFile;
import com.ryuqq.core.domain.git.Commit;
import com.ryuqq.core.storage.db.git.dto.ChangedFileDto;
import com.ryuqq.core.storage.db.git.dto.CommitDto;

@Component
public class CommitDomainMapper {

	private final ChangedFileDomainMapper changedFileDomainMapper;

	public CommitDomainMapper(ChangedFileDomainMapper changedFileDomainMapper) {
		this.changedFileDomainMapper = changedFileDomainMapper;
	}

	public Commit toDomain(CommitDto commitDto) {
		return Commit.create(
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
		return changedFiles.stream().map(changedFileDomainMapper::toDomain).toList();
	}
}
