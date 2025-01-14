package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.LabelDto;
import com.ryuqq.core.storage.db.git.dto.QLabelDto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ryuqq.core.storage.db.git.QLabelEntity.labelEntity;

@Repository
public class LabelQueryDslRepository implements LabelQueryRepository {

	private final JPAQueryFactory queryFactory;

	public LabelQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<LabelDto> fetchByLabelIn(List<String> names){
		return queryFactory
			.select(new QLabelDto(
				labelEntity.id,
				labelEntity.name
			))
			.from(labelEntity)
			.where(nameIn(names))
			.fetch();
	}

	private BooleanExpression nameIn(List<String> names){
		return labelEntity.name.in(names);
	}

}
