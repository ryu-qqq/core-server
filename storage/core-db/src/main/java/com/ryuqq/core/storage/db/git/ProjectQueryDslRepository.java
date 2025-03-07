package com.ryuqq.core.storage.db.git;

import static com.ryuqq.core.storage.db.git.QProjectEntity.projectEntity;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.storage.db.git.dto.ProjectDto;
import com.ryuqq.core.storage.db.git.dto.QProjectDto;

@Repository
public class ProjectQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public ProjectQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public Optional<ProjectDto> fetchByGitProjectIdAndGitType(long gitProjectId, GitType gitType) {
		return Optional.ofNullable(
			queryFactory.select(
					new QProjectDto(
						projectEntity.id,
						projectEntity.gitProjectId,
						projectEntity.gitType,
						projectEntity.repositoryName.coalesce(""),
						projectEntity.repositoryUrl.coalesce(""),
						projectEntity.owner.coalesce(""),
						projectEntity.description.coalesce("")
					)
				)
				.from(projectEntity)
				.where(
					gitProjectIdEq(gitProjectId),
					gitTypeEq(gitType)

				)
				.fetchOne()

		);
	}

	private BooleanExpression gitProjectIdEq(long gitProjectId){
		return projectEntity.gitProjectId.eq(gitProjectId);
	}


	private BooleanExpression gitTypeEq(GitType gitType){
		return null;
	}


}
