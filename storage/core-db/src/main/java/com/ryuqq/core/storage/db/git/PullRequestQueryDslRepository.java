package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.ReviewStatus;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.storage.db.git.dto.PullRequestDto;
import com.ryuqq.core.storage.db.git.dto.PullRequestStorageFilterDto;
import com.ryuqq.core.storage.db.git.dto.QPullRequestDto;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ryuqq.core.storage.db.git.QBranchEntity.branchEntity;
import static com.ryuqq.core.storage.db.git.QProjectEntity.projectEntity;
import static com.ryuqq.core.storage.db.git.QPullRequestEntity.pullRequestEntity;

@Repository
public class PullRequestQueryDslRepository implements PullRequestQueryRepository {

	private final JPAQueryFactory queryFactory;

	public PullRequestQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<PullRequestDto> fetchByFilter(PullRequestStorageFilterDto filterDto){
		var query = queryFactory
			.select(
				new QPullRequestDto(
					pullRequestEntity.id,
					pullRequestEntity.gitType,
					pullRequestEntity.gitPullId,
					pullRequestEntity.branchId,
					pullRequestEntity.sourceBranch,
					pullRequestEntity.targetBranch,
					pullRequestEntity.title.coalesce(""),
					pullRequestEntity.description.coalesce(""),
					pullRequestEntity.status,
					pullRequestEntity.reviewStatus,
					pullRequestEntity.createAt
				)
			)
			.from(pullRequestEntity)
			.innerJoin(branchEntity)
			.on(branchEntity.id.eq(pullRequestEntity.branchId))
			.innerJoin(projectEntity)
			.on(projectEntity.id.eq(branchEntity.projectId))
			.where(
				reviewStatusEq(filterDto.reviewStatus()),
				gitTypeEq(filterDto.gitType()),
				cursorBasedFilter(filterDto.cursorId(), filterDto.sort())
			)
			.limit(filterDto.pageSize() + 1);

		if (filterDto.cursorId() == null) {
			query = query.offset((long) filterDto.pageNumber() * filterDto.pageSize());
		}

		return query.fetch();
	}

	@Override
	public long countByFilter(PullRequestStorageFilterDto filterDto){
		Long count = queryFactory
			.select(pullRequestEntity.count())
			.from(pullRequestEntity)
			.where(
				reviewStatusEq(filterDto.reviewStatus()),
				gitTypeEq(filterDto.gitType())
			)
			.fetchOne();

		return count != null ? count : 0L;
	}

	private BooleanExpression gitTypeEq(GitType gitType){
		if(gitType == null){
			return null;
		}
		return pullRequestEntity.gitType.eq(gitType);
	}

	private BooleanExpression reviewStatusEq(ReviewStatus reviewStatus){
		if(reviewStatus == null){
			return null;
		}
		return pullRequestEntity.reviewStatus.eq(reviewStatus);
	}

	private BooleanExpression cursorBasedFilter(Long cursorId, Sort sort) {

		if (cursorId == null) {
			return null;
		}

		if (sort.equals(Sort.ASC)) {
			return pullRequestEntity.id.gt(cursorId);
		} else if (sort.equals(Sort.DESC)) {
			return pullRequestEntity.id.lt(cursorId);
		}

		return null;
	}

}
