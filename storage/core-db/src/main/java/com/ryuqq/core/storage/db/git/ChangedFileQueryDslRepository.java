package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.enums.GitType;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.enums.TestStatus;
import com.ryuqq.core.storage.db.git.dto.ChangedFileDto;
import com.ryuqq.core.storage.db.git.dto.ChangedFileRequestStorageFilterDto;
import com.ryuqq.core.storage.db.git.dto.QChangedFileDto;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ryuqq.core.storage.db.git.QBranchEntity.branchEntity;
import static com.ryuqq.core.storage.db.git.QChangedFileEntity.changedFileEntity;
import static com.ryuqq.core.storage.db.git.QCommitEntity.commitEntity;
import static com.ryuqq.core.storage.db.git.QProjectEntity.projectEntity;

@Repository
public class ChangedFileQueryDslRepository implements ChangedFileQueryRepository{

	private final JPAQueryFactory queryFactory;

	public ChangedFileQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<ChangedFileDto> fetchByFilter(ChangedFileRequestStorageFilterDto filterDto) {
		var query = queryFactory
			.select(
				new QChangedFileDto(
					changedFileEntity.id,
					changedFileEntity.commitId,
					changedFileEntity.gitCommitId,
					changedFileEntity.className,
					changedFileEntity.filePath,
					changedFileEntity.changeType,
					changedFileEntity.status
				)
			)
			.from(changedFileEntity)
			.innerJoin(commitEntity)
				.on(commitEntity.id.eq(changedFileEntity.commitId))
			.innerJoin(branchEntity)
				.on(branchEntity.id.eq(commitEntity.branchId))
			.innerJoin(projectEntity)
				.on(projectEntity.id.eq(branchEntity.projectId))
			.where(
				testStatusEq(filterDto.testStatus()),
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
	public long countByFilter(ChangedFileRequestStorageFilterDto filterDto) {
		Long count = queryFactory
			.select(changedFileEntity.count())
			.from(changedFileEntity)
			.innerJoin(commitEntity)
				.on(commitEntity.id.eq(changedFileEntity.commitId))
			.innerJoin(branchEntity)
				.on(branchEntity.id.eq(commitEntity.branchId))
			.innerJoin(projectEntity)
				.on(projectEntity.id.eq(branchEntity.projectId))
			.where(
				testStatusEq(filterDto.testStatus()),
				gitTypeEq(filterDto.gitType())
			)
			.fetchOne();

		return count !=null ? count : 0;
	}

	private BooleanExpression gitTypeEq(GitType gitType){
		return projectEntity.gitType.eq(gitType);
	}

	private BooleanExpression testStatusEq(TestStatus testStatus){
		return changedFileEntity.status.eq(testStatus);
	}

	private BooleanExpression cursorBasedFilter(Long cursorId, Sort sort) {
		if (cursorId == null) {
			return null;
		}

		if (sort.equals(Sort.ASC)) {
			return changedFileEntity.id.gt(cursorId);
		} else if (sort.equals(Sort.DESC)) {
			return changedFileEntity.id.lt(cursorId);
		}

		return null;
	}

}
