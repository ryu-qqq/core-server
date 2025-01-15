package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.BranchDto;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ryuqq.core.storage.db.git.QBranchEntity.branchEntity;
import static com.ryuqq.core.storage.db.git.QProjectEntity.projectEntity;

@Repository
public class BranchQueryDslRepository implements BranchQueryRepository{

	private final JPAQueryFactory queryFactory;

	public BranchQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public Optional<BranchDto> fetchByProjectIdAndBranchName(long projectId, String branchName) {
		return Optional.empty();
		// return Optional.ofNullable(
		// 					queryFactory.select(
		// 							new QBranchDto()
		// 						)
		// 						.from(branchEntity)
		// 						.innerJoin(projectEntity)
		// 							.on(projectEntity.id.eq(branchEntity.projectId))
		// 						.where(
		// 							gitProjectIdEq(gitProjectId), branchNameEq(branchName)
		// 						)
		// 						.fetchOne()
		// 				);
	}


	@Override
	public Optional<BranchDto> fetchByGitProjectIdAndBranchName(long gitProjectId, String branchName) {
		return Optional.empty();
		// return Optional.ofNullable(
		// 					queryFactory.select(
		// 							new QBranchDto()
		// 						)
		// 						.from(branchEntity)
		// 						.innerJoin(projectEntity)
		// 							.on(projectEntity.id.eq(branchEntity.projectId))
		// 						.where(
		// 							gitProjectIdEq(gitProjectId), branchNameEq(branchName)
		// 						)
		// 						.fetchOne()
		// 				);
	}


	private BooleanExpression gitProjectIdEq(long gitProjectId) {
		return projectEntity.gitProjectId.eq(gitProjectId);
	}

	private BooleanExpression branchNameEq(String branchName) {
		return branchEntity.branchName.eq(branchName);
	}



}
