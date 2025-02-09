package com.ryuqq.core.storage.db.brand;

import static com.ryuqq.core.storage.db.brand.QBrandEntity.brandEntity;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

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
                .where(brandIdEq(brandId))
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
                        .where(brandIdEq(brandId))
                        .fetchOne());
    }

    private BooleanExpression brandIdEq(long brandId){
        return brandEntity.id.eq(brandId);
    }

}
