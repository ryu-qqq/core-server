package com.ryuqq.core.storage.db.git;

import static com.ryuqq.core.storage.db.git.QLabelEntity.labelEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.storage.db.git.dto.LabelDto;
import com.ryuqq.core.storage.db.git.dto.QLabelDto;

@Repository
public class LabelQueryDslRepository  {

	private final JPAQueryFactory queryFactory;

	public LabelQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

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
