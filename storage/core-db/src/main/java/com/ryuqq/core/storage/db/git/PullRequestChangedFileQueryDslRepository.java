package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.PullRequestChangedFileDto;
import com.ryuqq.core.storage.db.git.dto.QPullRequestChangedFileDto;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ryuqq.core.storage.db.git.QBranchEntity.branchEntity;
import static com.ryuqq.core.storage.db.git.QChangedFileEntity.changedFileEntity;
import static com.ryuqq.core.storage.db.git.QCommitEntity.commitEntity;
import static com.ryuqq.core.storage.db.git.QProjectEntity.projectEntity;
import static com.ryuqq.core.storage.db.git.QPullRequestChangedFileEntity.pullRequestChangedFileEntity;

@Repository
public class PullRequestChangedFileQueryDslRepository implements PullRequestChangedFileQueryRepository {

	private final JPAQueryFactory queryFactory;

	public PullRequestChangedFileQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<PullRequestChangedFileDto> fetchById(long pullRequestId) {
		return queryFactory
			.select(
				new QPullRequestChangedFileDto(
					projectEntity.repositoryName,
					projectEntity.owner,
					pullRequestChangedFileEntity.pullRequestId,
					changedFileEntity.commitId,
					changedFileEntity.id,
					changedFileEntity.className,
					changedFileEntity.filePath,
					changedFileEntity.changeType,
					pullRequestChangedFileEntity.reviewStatus
				)
			)
			.from(pullRequestChangedFileEntity)
			.innerJoin(changedFileEntity)
				.on(pullRequestChangedFileEntity.changedFileId.eq(changedFileEntity.id))
			.innerJoin(branchEntity)
				.on(branchEntity.id.eq(commitEntity.branchId))
			.innerJoin(projectEntity)
				.on(projectEntity.id.eq(branchEntity.projectId))
			.where(
				pullRequestIdEq(pullRequestId)
			).fetch();
	}

	private BooleanExpression pullRequestIdEq(long pullRequestId){
		return pullRequestChangedFileEntity.pullRequestId.eq(pullRequestId);
	}

}
