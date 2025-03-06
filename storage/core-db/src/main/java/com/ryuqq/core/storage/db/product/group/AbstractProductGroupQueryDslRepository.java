package com.ryuqq.core.storage.db.product.group;

import static com.ryuqq.core.storage.db.product.delivery.QProductDeliveryEntity.productDeliveryEntity;
import static com.ryuqq.core.storage.db.product.group.QProductGroupEntity.productGroupEntity;
import static com.ryuqq.core.storage.db.product.image.QProductDetailDescriptionEntity.productDetailDescriptionEntity;
import static com.ryuqq.core.storage.db.product.image.QProductGroupImageEntity.productGroupImageEntity;
import static com.ryuqq.core.storage.db.product.notice.QProductNoticeEntity.productNoticeEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.enums.Origin;
import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;
import com.ryuqq.core.storage.db.QueryDslFetchStrategy;
import com.ryuqq.core.storage.db.product.ProductSortFieldOrderResolver;
import com.ryuqq.core.storage.db.product.delivery.QProductDeliveryDto;
import com.ryuqq.core.storage.db.product.image.QProductDetailDescriptionDto;
import com.ryuqq.core.storage.db.product.image.QProductGroupImageDto;
import com.ryuqq.core.storage.db.product.notice.QProductNoticeDto;

public abstract class AbstractProductGroupQueryDslRepository implements ProductGroupQueryDslRepository {

	protected final JPAQueryFactory queryFactory;

	public AbstractProductGroupQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public Optional<ProductGroupContextDto> fetchContextById(long productGroupId) {
		return fetchContextByIds(List.of(productGroupId))
			.stream()
			.findFirst();
	}

	protected List<Long> fetchIdsByCondition(QueryDslFetchStrategy<ProductGroupEntity> strategy) {
		JPAQuery<Long> query = queryFactory
			.select(productGroupEntity.id)
			.from(productGroupEntity)
			.where(strategy.getWhereCondition())
			.orderBy(strategy.getSortOrder())
			.limit(strategy.getLimitSize() + 1);

		if (!strategy.isUseCursorPaging()) {
			int offset = Math.max(0, (strategy.getPage() - 1)) * strategy.getLimitSize();
			query.offset((long) offset);
		}

		return query.fetch();
	}

	protected long countByCondition(BooleanBuilder whereCondition) {
		Long count = queryFactory
			.select(productGroupEntity.count())
			.from(productGroupEntity)
			.where(whereCondition)
			.fetchOne();

		return count !=null ? count : 0;
	}


	protected Long getCursorIdForPage(ProductGroupSearchCondition condition, BooleanBuilder whereCondition) {
		return queryFactory
			.select(productGroupEntity.id)
			.from(productGroupEntity)
			.where(whereCondition)
			.orderBy(ProductSortFieldOrderResolver.resolveSortOrder(condition.getProductSortField(), condition.getSort()))
			.offset((long) (condition.getPage() - 1) * condition.getSize())
			.limit(1)
			.fetchOne();
	}


	protected List<ProductGroupContextDto> fetchContextByIds(List<Long> productGroupIds) {
		return queryFactory
			.from(productGroupEntity)
			.leftJoin(productNoticeEntity).on(productNoticeEntity.productGroupId.eq(productGroupEntity.id))
			.leftJoin(productDeliveryEntity).on(productDeliveryEntity.productGroupId.eq(productGroupEntity.id))
			.leftJoin(productDetailDescriptionEntity).on(productDetailDescriptionEntity.productGroupId.eq(productGroupEntity.id))
			.leftJoin(productGroupImageEntity).on(productGroupImageEntity.productGroupId.eq(productGroupEntity.id))
			.on(productGroupImageEntity.deleted.eq(false))
			.where(productGroupEntity.id.in(productGroupIds))
			.transform(
				GroupBy.groupBy(productGroupEntity.id).list(
					new QProductGroupContextDto(
						new QProductGroupDto(
							productGroupEntity.id,
							productGroupEntity.sellerId,
							productGroupEntity.categoryId,
							productGroupEntity.brandId,
							productGroupEntity.productGroupName,
							productGroupEntity.styleCode,
							productGroupEntity.productCondition,
							productGroupEntity.managementType,
							productGroupEntity.optionType,
							productGroupEntity.regularPrice,
							productGroupEntity.currentPrice,
							productGroupEntity.salePrice,
							productGroupEntity.discountRate,
							productGroupEntity.soldOut,
							productGroupEntity.displayed,
							productGroupEntity.productStatus,
							productGroupEntity.keywords.coalesce(""),
							productGroupEntity.createdAt,
							productGroupEntity.updatedAt
						),
						new QProductNoticeDto(
							productNoticeEntity.productGroupId,
							productNoticeEntity.material.coalesce("상세 정보 참고"),
							productNoticeEntity.color.coalesce("상세 정보 참고"),
							productNoticeEntity.size.coalesce("상세 정보 참고"),
							productNoticeEntity.maker.coalesce("상세 정보 참고"),
							productNoticeEntity.origin.coalesce(Origin.OTHER),
							productNoticeEntity.washingMethod.coalesce("상세 정보 참고"),
							productNoticeEntity.yearMonth.coalesce("상세 정보 참고"),
							productNoticeEntity.assuranceStandard.coalesce("상세 정보 참고"),
							productNoticeEntity.asPhone.coalesce("상세 정보 참고")
						),
						new QProductDeliveryDto(
							productDeliveryEntity.productGroupId,
							productDeliveryEntity.deliveryArea.coalesce("상세 정보 참고"),
							productDeliveryEntity.deliveryFee.coalesce(BigDecimal.ZERO),
							productDeliveryEntity.deliveryPeriodAverage.coalesce(3),
							productDeliveryEntity.returnMethodDomestic.coalesce(ReturnMethod.NOT_PROCEED_RETURN),
							productDeliveryEntity.returnCourierDomestic.coalesce(ShipmentCompanyCode.REFER_DETAIL),
							productDeliveryEntity.returnChargeDomestic.coalesce(BigDecimal.ZERO),
							productDeliveryEntity.returnExchangeAreaDomestic.coalesce("상세 정보 참고")
						),
						GroupBy.list(
							new QProductGroupImageDto(
								productGroupImageEntity.id,
								productGroupImageEntity.productGroupId,
								productGroupImageEntity.productImageType,
								productGroupImageEntity.imageUrl.coalesce(""),
								productGroupImageEntity.originUrl.coalesce(""),
								productGroupImageEntity.displayOrder,
								productGroupImageEntity.deleted
							)
						),
						new QProductDetailDescriptionDto(
							productDetailDescriptionEntity.productGroupId,
							productDetailDescriptionEntity.detailDescription.coalesce("")
						)
					)
				)
			);
	}



}
