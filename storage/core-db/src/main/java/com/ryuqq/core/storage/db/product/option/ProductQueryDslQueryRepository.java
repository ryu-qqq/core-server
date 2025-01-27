package com.ryuqq.core.storage.db.product.option;

import static com.ryuqq.core.storage.db.product.option.QOptionDetailEntity.optionDetailEntity;
import static com.ryuqq.core.storage.db.product.option.QOptionGroupEntity.optionGroupEntity;
import static com.ryuqq.core.storage.db.product.option.QProductEntity.productEntity;
import static com.ryuqq.core.storage.db.product.option.QProductOptionEntity.productOptionEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class ProductQueryDslQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

	public List<ProductContextDto> fetchByProductGroupIds(List<Long> productGroupIds) {
		return queryFactory
			.select(
				new QProductContextDto(
					productEntity.productGroupId,
					productEntity.id,
					productEntity.quantity,
					productEntity.soldOut,
					productEntity.displayed,
					productEntity.deleted,
					productOptionEntity.id,
					optionGroupEntity.id,
					optionDetailEntity.id,
					optionGroupEntity.optionName,
					optionDetailEntity.optionValue,
					productEntity.additionalPrice.coalesce(BigDecimal.ZERO)
				)
			)
			.from(productEntity)
			.leftJoin(productOptionEntity)
			.on(productOptionEntity.productId.eq(productEntity.id))
			.on(productOptionEntity.deleted.eq(false))
			.leftJoin(optionGroupEntity)
			.on(optionGroupEntity.id.eq(productOptionEntity.optionGroupId))
			.on(optionGroupEntity.deleted.eq(false))
			.leftJoin(optionDetailEntity)
			.on(optionDetailEntity.id.eq(productOptionEntity.optionDetailId))
			.on(optionDetailEntity.deleted.eq(false))
			.where(
				productGroupIdIn(productGroupIds),
				notDeleted()
			)
			.orderBy(productEntity.id.asc())
			.fetch();
	}

	private BooleanExpression productGroupIdIn(List<Long> productGroupIds) {
		if(productGroupIds.isEmpty()) return null;
        return productEntity.productGroupId.in(productGroupIds);
    }


	private BooleanExpression notDeleted() {
		return productEntity.deleted.eq(false);
	}


}
