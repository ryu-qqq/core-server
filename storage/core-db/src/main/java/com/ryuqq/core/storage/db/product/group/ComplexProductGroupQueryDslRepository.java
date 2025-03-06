package com.ryuqq.core.storage.db.product.group;

import static com.ryuqq.core.storage.db.product.group.QProductGroupEntity.productGroupEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.storage.db.QueryDslFetchStrategy;
import com.ryuqq.core.storage.db.product.ComplexProductGroupIdBooleanBuilderFactory;

@Repository
public class ComplexProductGroupQueryDslRepository extends AbstractProductGroupQueryDslRepository {

	public ComplexProductGroupQueryDslRepository(JPAQueryFactory queryFactory) {
		super(queryFactory);
	}

	@Override
	public List<ProductGroupContextDto> fetchContextByCondition(
		ProductGroupSearchCondition productGroupSearchCondition) {
		QueryDslFetchStrategy<ProductGroupEntity> fetchStrategy = determineFetchStrategy(productGroupSearchCondition);

		List<Long> productGroupIds = fetchIdsByCondition(fetchStrategy);

		if (productGroupIds.isEmpty()) {
			return List.of();
		}
		return fetchContextByIds(productGroupIds);
	}

	@Override
	public long countByCondition(ProductGroupSearchCondition productGroupSearchCondition) {
		BooleanBuilder whereCondition = ComplexProductGroupIdBooleanBuilderFactory.buildComplexCondition(productGroupSearchCondition);
		return countByCondition(whereCondition);
	}

	protected QueryDslFetchStrategy<ProductGroupEntity> determineFetchStrategy(ProductGroupSearchCondition condition) {
		BooleanBuilder whereCondition = ComplexProductGroupIdBooleanBuilderFactory.buildComplexCondition(condition);
		Long cursorId = condition.getCursorId();

		if (cursorId == null && condition.getPage() >= 0 && condition.getSize() != 0) {
			int offset = condition.getPage() * condition.getSize();
			if (offset > 1000) {
				cursorId = getCursorIdForPage(condition, whereCondition);
			}
		}

		return QueryDslFetchStrategy.create(
			cursorId,
			condition.getSize(),
			condition.getPage(),
			condition.getSort(),
			productGroupEntity,
			entity -> productGroupEntity.id,
			whereCondition
		);
	}



}
