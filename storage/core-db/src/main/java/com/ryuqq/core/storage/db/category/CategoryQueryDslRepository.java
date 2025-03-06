package com.ryuqq.core.storage.db.category;

import static com.ryuqq.core.storage.db.brand.QBrandEntity.brandEntity;
import static com.ryuqq.core.storage.db.category.QCategoryEntity.categoryEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.Union;

import com.ryuqq.core.domain.category.core.CategorySearchCondition;
import com.ryuqq.core.storage.db.QueryDslFetchStrategy;
import com.ryuqq.core.storage.db.category.dto.CategoryDto;
import com.ryuqq.core.storage.db.category.dto.QCategoryDto;

import jakarta.persistence.EntityManager;

@Repository
public class CategoryQueryDslRepository {

	private final JPAQueryFactory queryFactory;
	private final EntityManager em;

	public CategoryQueryDslRepository(JPAQueryFactory queryFactory, EntityManager em) {
		this.queryFactory = queryFactory;
		this.em = em;
	}

	public boolean existById(long categoryId) {
		Long categoryOpt = queryFactory
			.select(categoryEntity.id)
			.from(categoryEntity)
			.where(categoryEntity.id.eq(categoryId))
			.fetchFirst();

		return categoryOpt != null;
	}

	public List<CategoryDto> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation) {
		JPASQLQuery<CategoryEntity> q = new JPASQLQuery<>(em, SQLTemplates.DEFAULT);
		QCategoryEntity c = new QCategoryEntity("c");
		QCategoryEntity sub = new QCategoryEntity("sub");
		EntityPathBase<QCategoryEntity> rec = new EntityPathBase<>(QCategoryEntity.class, "sub");

		JPQLQuery<CategoryEntity> query1 = JPAExpressions.select(
				Projections.fields(CategoryEntity.class, c.id, c.categoryName, c.depth, c.parentCategoryId, c.displayed, c.targetGroup, c.categoryType, c.path))
			.from(c)
			.where(c.id.in(categoryIds));

		JPQLQuery<CategoryEntity> query2 = isParentRelation
			? JPAExpressions.select(Projections.fields(CategoryEntity.class, c.id, c.categoryName, c.depth, c.parentCategoryId, c.displayed, c.targetGroup, c.categoryType, c.path))
			.from(rec)
			.innerJoin(c).on(sub.id.eq(c.parentCategoryId))
			: JPAExpressions.select(Projections.fields(CategoryEntity.class, c.id, c.categoryName, c.depth, c.parentCategoryId, c.displayed, c.targetGroup, c.categoryType, c.path))
			.from(rec)
			.innerJoin(c).on(c.id.eq(sub.parentCategoryId));

		Union<CategoryEntity> union = SQLExpressions.unionAll(query1, query2);

		return q.withRecursive(rec, c.id, c.categoryName, c.depth, c.parentCategoryId, c.displayed, c.targetGroup, c.categoryType, c.path).as(union)
			.select(
				new QCategoryDto(
					sub.id,
					sub.categoryName,
					sub.depth,
					sub.parentCategoryId,
					sub.displayed,
					sub.targetGroup,
					sub.categoryType,
					sub.path
				))
			.from(rec)
			.orderBy(isParentRelation ? sub.depth.asc() : sub.depth.desc())
			.fetch();
	}

	public List<CategoryDto> fetchByCondition(CategorySearchCondition categorySearchCondition){

		QueryDslFetchStrategy<CategoryEntity> fetchStrategy = determineFetchStrategy(categorySearchCondition);

		JPAQuery<CategoryDto> query = queryFactory
			.select(
				new QCategoryDto(
					categoryEntity.id,
					categoryEntity.categoryName,
					categoryEntity.depth,
					categoryEntity.parentCategoryId,
					categoryEntity.displayed,
					categoryEntity.targetGroup,
					categoryEntity.categoryType,
					categoryEntity.path
				)
			)
			.from(categoryEntity)
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

	protected long countByCondition(CategorySearchCondition categorySearchCondition) {

		QueryDslFetchStrategy<CategoryEntity> fetchStrategy = determineFetchStrategy(categorySearchCondition);

		Long count = queryFactory
			.select(categoryEntity.count())
			.from(categoryEntity)
			.where(fetchStrategy.getWhereCondition())
			.fetchOne();

		return count !=null ? count : 0;
	}

	private QueryDslFetchStrategy<CategoryEntity> determineFetchStrategy(CategorySearchCondition condition) {
		BooleanBuilder whereCondition = DefaultCategoryBooleanBuilderFactory.buildBaseCondition(condition);
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
			categoryEntity,
			entity -> categoryEntity.id,
			whereCondition
		);
	}

	private Long getCursorIdForPage(CategorySearchCondition condition, BooleanBuilder whereCondition) {
		return queryFactory
			.select(brandEntity.id)
			.from(brandEntity)
			.where(whereCondition)
			.orderBy(CategorySortFieldOrderResolver.resolveSortOrder(condition.getCategorySortField(), condition.getSort()))
			.offset((long) (condition.getPage() - 1) * condition.getSize())
			.limit(1)
			.fetchOne();
	}



}
