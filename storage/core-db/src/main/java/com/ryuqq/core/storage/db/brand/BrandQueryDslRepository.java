package com.ryuqq.core.storage.db.brand;

import static com.ryuqq.core.storage.db.brand.QBrandEntity.brandEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.domain.brand.core.BrandSearchCondition;
import com.ryuqq.core.storage.db.QueryDslFetchStrategy;
import com.ryuqq.core.storage.db.brand.dto.BrandDto;
import com.ryuqq.core.storage.db.brand.dto.QBrandDto;

@Repository
public class BrandQueryDslRepository  {

    private final JPAQueryFactory queryFactory;

    public BrandQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public boolean existById(long brandId) {
        Long brandOpt = queryFactory
                .select(brandEntity.id)
                .from(brandEntity)
                .where(brandEntity.id.eq(brandId))
                .fetchFirst();

        return brandOpt != null;
    }

    public Optional<BrandDto> fetchById(long brandId) {
        return Optional.ofNullable(queryFactory
                        .select(
                                new QBrandDto(
                                        brandEntity.id,
                                        brandEntity.brandName,
                                        brandEntity.brandNameKr,
                                        brandEntity.displayed
                                )
                        )
                        .from(brandEntity)
                        .where(brandEntity.id.eq(brandId))
                        .fetchOne());
    }

	public List<BrandDto> fetchByCondition(BrandSearchCondition brandSearchCondition) {

		QueryDslFetchStrategy<BrandEntity> fetchStrategy = determineFetchStrategy(brandSearchCondition);

		JPAQuery<BrandDto> query = queryFactory
			.select(
				new QBrandDto(
					brandEntity.id,
					brandEntity.brandName,
					brandEntity.brandNameKr,
					brandEntity.displayed
				)
			)
			.from(brandEntity)
			.where(fetchStrategy.getWhereCondition())
			.orderBy(fetchStrategy.getSortOrder())
			.limit(fetchStrategy.getLimitSize()
				+ 1);

		if (!fetchStrategy.isUseCursorPaging()) {
			int offset = Math.max(0, (fetchStrategy.getPage() - 1)) * fetchStrategy.getLimitSize();
			query.offset((long) offset);
		}

		return query.fetch();
	}

	protected long countByCondition(BrandSearchCondition brandSearchCondition) {

		QueryDslFetchStrategy<BrandEntity> fetchStrategy = determineFetchStrategy(brandSearchCondition);

		Long count = queryFactory
			.select(brandEntity.count())
			.from(brandEntity)
			.where(fetchStrategy.getWhereCondition())
			.fetchOne();

		return count !=null ? count : 0;
	}


	private QueryDslFetchStrategy<BrandEntity> determineFetchStrategy(BrandSearchCondition condition) {
		BooleanBuilder whereCondition = DefaultBrandBooleanBuilderFactory.buildBaseCondition(condition);
		Long cursorId = condition.getCursorId();

		if (cursorId == null && condition.getPage() >= 0 && condition.getSize() != 0) {
			int offset = condition.getPage() * condition.getSize();
			if (offset > 100) {
				cursorId = getCursorIdForPage(condition, whereCondition);
			}
		}

		return QueryDslFetchStrategy.create(
			cursorId,
			condition.getSize(),
			condition.getPage(),
			condition.getSort(),
			brandEntity,
			entity -> brandEntity.id,
			whereCondition
		);
	}

	private Long getCursorIdForPage(BrandSearchCondition condition, BooleanBuilder whereCondition) {
		return queryFactory
			.select(brandEntity.id)
			.from(brandEntity)
			.where(whereCondition)
			.orderBy(BrandSortFieldOrderResolver.resolveSortOrder(condition.getBrandSortField(), condition.getSort()))
			.offset((long) (condition.getPage() - 1) * condition.getSize())
			.limit(1)
			.fetchOne();
	}

}
