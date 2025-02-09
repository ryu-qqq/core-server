package com.ryuqq.core.storage.db.category;

import static com.ryuqq.core.storage.db.category.QCategoryEntity.categoryEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.Union;

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
			.where(categoryIdEq(categoryId))
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
			.where(c.id.in(categoryIds)); // 변경: 다중 ID를 처리

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

	private BooleanExpression categoryIdEq(long categoryId){
		return categoryEntity.id.eq(categoryId);
	}

	private BooleanExpression categoryIdIn(List<Long> categoryIds){
		return (categoryIds != null && !categoryIds.isEmpty()) ? categoryEntity.id.in(categoryIds) : null;
	}

}
