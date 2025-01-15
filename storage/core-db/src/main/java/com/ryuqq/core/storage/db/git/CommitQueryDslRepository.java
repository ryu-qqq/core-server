package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.CommitDto;
import com.ryuqq.core.storage.db.git.dto.QChangedFileDto;
import com.ryuqq.core.storage.db.git.dto.QCommitDto;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ryuqq.core.storage.db.git.QChangedFileEntity.changedFileEntity;
import static com.ryuqq.core.storage.db.git.QCommitEntity.commitEntity;

@Repository
public class CommitQueryDslRepository implements CommitQueryRepository{

	private final JPAQueryFactory queryFactory;

	public CommitQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<CommitDto> fetchByGitCommitIdIn(List<String> gitCommitIds) {
		return queryFactory
			.from(commitEntity)
			.innerJoin(changedFileEntity)
			.on(changedFileEntity.commitId.eq(commitEntity.id))
			.where(gitCommitIdIn(gitCommitIds))
			.transform(
				GroupBy.groupBy(commitEntity.id).list(
					new QCommitDto(
						commitEntity.id,
						commitEntity.branchId,
						commitEntity.gitCommitId,
						commitEntity.author,
						commitEntity.commitMessage,
						commitEntity.timestamp,
						GroupBy.list(
							new QChangedFileDto(
								changedFileEntity.id,
								changedFileEntity.commitId,
								changedFileEntity.className,
								changedFileEntity.filePath,
								changedFileEntity.changeType
							)

						)
					)
				)
			);
	}

	private BooleanExpression gitCommitIdIn(List<String> gitCommitIds) {
		return commitEntity.gitCommitId.in(gitCommitIds);
	}

}
