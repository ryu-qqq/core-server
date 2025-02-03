package com.ryuqq.core.storage.db.git;

import static com.ryuqq.core.storage.db.git.QBranchEntity.branchEntity;
import static com.ryuqq.core.storage.db.git.QProjectEntity.projectEntity;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.storage.db.git.dto.BranchDto;
import com.ryuqq.core.storage.db.git.dto.QBranchDto;

@Repository
public class BranchQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public BranchQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public Optional<BranchDto> fetchByProjectIdAndBranchName(long projectId, String branchName) {
		return Optional.ofNullable(
							queryFactory.select(
									new QBranchDto(
										branchEntity.id,
										branchEntity.projectId,
										branchEntity.branchName,
										branchEntity.baseBranchName
									)
								)
								.from(branchEntity)
								.innerJoin(projectEntity)
									.on(projectEntity.id.eq(branchEntity.projectId))
								.where(
									projectIdEq(projectId), branchNameEq(branchName)
								)
								.fetchOne()
						);
	}



	public Optional<BranchDto> fetchByGitProjectIdAndBranchName(long gitProjectId, String branchName) {
		return Optional.ofNullable(
							queryFactory.select(
									new QBranchDto(
										branchEntity.id,
										branchEntity.projectId,
										branchEntity.branchName,
										branchEntity.baseBranchName
									)
								)
								.from(branchEntity)
								.innerJoin(projectEntity)
									.on(projectEntity.id.eq(branchEntity.projectId))
								.where(
									gitProjectIdEq(gitProjectId), branchNameEq(branchName)
								)
								.fetchOne()
						);
	}


	private BooleanExpression gitProjectIdEq(long gitProjectId) {
		return projectEntity.gitProjectId.eq(gitProjectId);
	}

	private BooleanExpression projectIdEq(long projectId) {
		return branchEntity.projectId.eq(projectId);
	}

	private BooleanExpression branchNameEq(String branchName) {
		return branchEntity.branchName.eq(branchName);
	}



}
