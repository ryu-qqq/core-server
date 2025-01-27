package com.ryuqq.core.storage.db.git;

import static com.ryuqq.core.storage.db.git.QPullRequestChangedFileEntity.pullRequestChangedFileEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.storage.db.git.dto.PullRequestChangedFileDto;

@Repository
public class PullRequestChangedFileQueryDslRepository  {

	private final JPAQueryFactory queryFactory;

	public PullRequestChangedFileQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}


	public List<PullRequestChangedFileDto> fetchById(long pullRequestId) {
		return List.of();
		// return queryFactory
		// 	.select(
		// 		new QPullRequestChangedFileDto(
		// 			projectEntity.repositoryName,
		// 			projectEntity.owner,
		// 			pullRequestChangedFileEntity.pullRequestId,
		// 			changedFileEntity.commitId,
		// 			changedFileEntity.id,
		// 			changedFileEntity.className,
		// 			changedFileEntity.filePath,
		// 			changedFileEntity.changeType,
		// 			pullRequestChangedFileEntity.reviewStatus
		// 		)
		// 	)
		// 	.from(pullRequestChangedFileEntity)
		// 	.innerJoin(changedFileEntity)
		// 		.on(pullRequestChangedFileEntity.changedFileId.eq(changedFileEntity.id))
		// 	.innerJoin(commitEntity)
		// 		.on(commitEntity.id.eq(changedFileEntity.commitId))
		// 	.innerJoin(branchEntity)
		// 		.on(branchEntity.id.eq(commitEntity.branchId))
		// 	.innerJoin(projectEntity)
		// 		.on(projectEntity.id.eq(branchEntity.projectId))
		// 	.where(
		// 		pullRequestIdEq(pullRequestId)
		// 	).fetch();
	}

	private BooleanExpression pullRequestIdEq(long pullRequestId){
		return pullRequestChangedFileEntity.pullRequestId.eq(pullRequestId);
	}

}
