package com.ryuqq.core.storage.db.seller;

import static com.ryuqq.core.storage.db.seller.QSellerEntity.sellerEntity;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.storage.db.seller.dto.QSellerDto;
import com.ryuqq.core.storage.db.seller.dto.SellerDto;

@Repository
public class SellerQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public SellerQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public boolean existById(long id){
		Long sellerId = queryFactory.select(sellerEntity.id)
				.from(sellerEntity)
				.where(sellerIdEq(id))
				.fetchOne();


		return sellerId != null;
	}

	public Optional<SellerDto> fetchById(long id){
		return Optional.ofNullable(
			queryFactory.select(
					new QSellerDto(
						sellerEntity.id,
						sellerEntity.sellerName
					)
				)
				.from(sellerEntity)
			.where(sellerIdEq(id))
				.fetchOne()

		);
	}

	private BooleanExpression sellerIdEq(long id){
		return sellerEntity.id.eq(id);
	}

}
