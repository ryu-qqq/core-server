package com.ryuqq.core.storage.db.external;

import static com.ryuqq.core.storage.db.external.QExternalBrandEntity.externalBrandEntity;
import static com.ryuqq.core.storage.db.external.QExternalCategoryEntity.externalCategoryEntity;
import static com.ryuqq.core.storage.db.external.QExternalProductGroupEntity.externalProductGroupEntity;
import static com.ryuqq.core.storage.db.external.QExternalSiteSellerEntity.externalSiteSellerEntity;
import static com.ryuqq.core.storage.db.external.QSiteEntity.siteEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.enums.SyncStatus;
import com.ryuqq.core.storage.db.external.dto.ExternalProductGroupDto;
import com.ryuqq.core.storage.db.external.dto.QExternalProductGroupDto;

@Repository
public class ExternalProductGroupQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public ExternalProductGroupQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}


	public List<ExternalProductGroupDto> fetchByProductGroupIdsAndStatus(List<Long> productGroupIds, SyncStatus status) {
		List<Long> externalProductGroupIds = fetchExternalProductGroupIds(null, productGroupIds, status);

		return queryFactory
			.from(externalProductGroupEntity)
			.innerJoin(siteEntity)
				.on(siteEntity.id.eq(externalProductGroupEntity.siteId))
			.innerJoin(externalSiteSellerEntity)
				.on(externalSiteSellerEntity.siteId.eq(siteEntity.id))
				.on(externalSiteSellerEntity.sellerId.eq(externalProductGroupEntity.sellerId))
			.leftJoin(externalBrandEntity)
				.on(externalBrandEntity.internalBrandId.eq(externalProductGroupEntity.brandId))
				.on(externalBrandEntity.siteId.eq(externalProductGroupEntity.siteId))
			.leftJoin(externalCategoryEntity)
				.on(externalCategoryEntity.internalCategoryId.eq(externalProductGroupEntity.brandId))
				.on(externalCategoryEntity.siteId.eq(externalProductGroupEntity.siteId))
			.where(
				externalProductGroupIdIn(externalProductGroupIds)
			).transform(
				GroupBy.groupBy(externalProductGroupEntity.id).list(
					new QExternalProductGroupDto(
						externalProductGroupEntity.id,
						externalProductGroupEntity.siteId,
						siteEntity.name,
						externalSiteSellerEntity.sellerName,
						externalProductGroupEntity.sellerId,
						externalProductGroupEntity.productGroupId,
						externalProductGroupEntity.brandId,
						externalBrandEntity.externalBrandId,
						externalProductGroupEntity.categoryId,
						externalCategoryEntity.externalCategoryId,
						externalProductGroupEntity.externalProductGroupId,
						externalProductGroupEntity.productName,
						externalProductGroupEntity.regularPrice,
						externalProductGroupEntity.currentPrice,
						externalProductGroupEntity.status,
						externalProductGroupEntity.fixedPrice,
						externalProductGroupEntity.soldOut,
						externalProductGroupEntity.displayed
					)
				)
			);

	}

	public List<ExternalProductGroupDto> fetchBySiteIdsAndProductGroupIds(List<Long> productGroupIds, List<Long> siteIds){

		List<Long> externalProductGroupIds = fetchExternalProductGroupIds(siteIds, productGroupIds, null);

		return queryFactory.select(
				new QExternalProductGroupDto(
					externalProductGroupEntity.id,
					externalProductGroupEntity.siteId,
					siteEntity.name,
					externalSiteSellerEntity.sellerName,
					externalProductGroupEntity.sellerId,
					externalProductGroupEntity.productGroupId,
					externalProductGroupEntity.brandId,
					externalBrandEntity.externalBrandId,
					externalProductGroupEntity.categoryId,
					externalCategoryEntity.externalCategoryId,
					externalProductGroupEntity.externalProductGroupId,
					externalProductGroupEntity.productName,
					externalProductGroupEntity.regularPrice,
					externalProductGroupEntity.currentPrice,
					externalProductGroupEntity.status,
					externalProductGroupEntity.fixedPrice,
					externalProductGroupEntity.soldOut,
					externalProductGroupEntity.displayed
				))
			.from(externalProductGroupEntity)
			.innerJoin(siteEntity)
				.on(siteEntity.id.eq(externalProductGroupEntity.siteId))
			.innerJoin(externalSiteSellerEntity)
				.on(externalSiteSellerEntity.siteId.eq(siteEntity.id))
				.on(externalSiteSellerEntity.sellerId.eq(externalProductGroupEntity.sellerId))
			.leftJoin(externalBrandEntity)
				.on(externalBrandEntity.internalBrandId.eq(externalProductGroupEntity.brandId))
				.on(externalBrandEntity.siteId.eq(externalProductGroupEntity.siteId))
			.leftJoin(externalCategoryEntity)
				.on(externalCategoryEntity.internalCategoryId.eq(externalProductGroupEntity.brandId))
				.on(externalCategoryEntity.siteId.eq(externalProductGroupEntity.siteId))
			.where(
				externalProductGroupIdIn(externalProductGroupIds)
			).transform(
					GroupBy.groupBy(externalProductGroupEntity.id).list(
						new QExternalProductGroupDto(
							externalProductGroupEntity.id,
							externalProductGroupEntity.siteId,
							siteEntity.name,
							externalSiteSellerEntity.sellerName,
							externalProductGroupEntity.sellerId,
							externalProductGroupEntity.productGroupId,
							externalProductGroupEntity.brandId,
							externalBrandEntity.externalBrandId,
							externalProductGroupEntity.categoryId,
							externalCategoryEntity.externalCategoryId,
							externalProductGroupEntity.externalProductGroupId,
							externalProductGroupEntity.productName,
							externalProductGroupEntity.regularPrice,
							externalProductGroupEntity.currentPrice,
							externalProductGroupEntity.status,
							externalProductGroupEntity.fixedPrice,
							externalProductGroupEntity.soldOut,
							externalProductGroupEntity.displayed
						)
					)
				);
	}

	public Optional<ExternalProductGroupDto> fetchBySiteIdAndProductGroupId(long productGroupId, long siteId){
		return Optional.ofNullable(
			queryFactory.select(
					new QExternalProductGroupDto(
						externalProductGroupEntity.id,
						externalProductGroupEntity.siteId,
						siteEntity.name,
						externalSiteSellerEntity.sellerName,
						externalProductGroupEntity.sellerId,
						externalProductGroupEntity.productGroupId,
						externalProductGroupEntity.brandId,
						externalBrandEntity.externalBrandId,
						externalProductGroupEntity.categoryId,
						externalCategoryEntity.externalCategoryId,
						externalProductGroupEntity.externalProductGroupId,
						externalProductGroupEntity.productName,
						externalProductGroupEntity.regularPrice,
						externalProductGroupEntity.currentPrice,
						externalProductGroupEntity.status,
						externalProductGroupEntity.fixedPrice,
						externalProductGroupEntity.soldOut,
						externalProductGroupEntity.displayed
					))
				.from(externalProductGroupEntity)
				.innerJoin(siteEntity)
					.on(siteEntity.id.eq(externalProductGroupEntity.siteId))
				.innerJoin(externalSiteSellerEntity)
					.on(externalSiteSellerEntity.siteId.eq(siteEntity.id))
					.on(externalSiteSellerEntity.sellerId.eq(externalProductGroupEntity.sellerId))
				.leftJoin(externalBrandEntity)
					.on(externalBrandEntity.internalBrandId.eq(externalProductGroupEntity.brandId))
					.on(externalBrandEntity.siteId.eq(externalProductGroupEntity.siteId))
				.leftJoin(externalCategoryEntity)
					.on(externalCategoryEntity.internalCategoryId.eq(externalProductGroupEntity.brandId))
					.on(externalCategoryEntity.siteId.eq(externalProductGroupEntity.siteId))
				.where(
					productGroupIdEq(productGroupId), siteIdEq(siteId)
				).transform(
					GroupBy.groupBy(externalProductGroupEntity.productGroupId).as(
						new QExternalProductGroupDto(
							externalProductGroupEntity.id,
							externalProductGroupEntity.siteId,
							siteEntity.name,
							externalSiteSellerEntity.sellerName,
							externalProductGroupEntity.sellerId,
							externalProductGroupEntity.productGroupId,
							externalProductGroupEntity.brandId,
							externalBrandEntity.externalBrandId,
							externalProductGroupEntity.categoryId,
							externalCategoryEntity.externalCategoryId,
							externalProductGroupEntity.externalProductGroupId,
							externalProductGroupEntity.productName,
							externalProductGroupEntity.regularPrice,
							externalProductGroupEntity.currentPrice,
							externalProductGroupEntity.status,
							externalProductGroupEntity.fixedPrice,
							externalProductGroupEntity.soldOut,
							externalProductGroupEntity.displayed
						)
					)
				).get(productGroupId));
	}

	public List<ExternalProductGroupDto> fetchBySiteIdAndStatus(long siteId, SyncStatus status) {
		List<Long> externalProductGroupIds = fetchExternalProductGroupIds(List.of(siteId), null, status);
		return queryFactory
			.from(externalProductGroupEntity)
			.innerJoin(siteEntity)
				.on(siteEntity.id.eq(externalProductGroupEntity.siteId))
			.innerJoin(externalSiteSellerEntity)
				.on(externalSiteSellerEntity.siteId.eq(siteEntity.id))
				.on(externalSiteSellerEntity.sellerId.eq(externalProductGroupEntity.sellerId))
			.leftJoin(externalBrandEntity)
				.on(externalBrandEntity.internalBrandId.eq(externalProductGroupEntity.brandId))
				.on(externalBrandEntity.siteId.eq(externalProductGroupEntity.siteId))
			.leftJoin(externalCategoryEntity)
				.on(externalCategoryEntity.internalCategoryId.eq(externalProductGroupEntity.brandId))
				.on(externalCategoryEntity.siteId.eq(externalProductGroupEntity.siteId))
			.where(externalProductGroupIdIn(externalProductGroupIds))
			.transform(
					GroupBy.groupBy(externalProductGroupEntity.id).list(
						new QExternalProductGroupDto(
							externalProductGroupEntity.id,
							externalProductGroupEntity.siteId,
							siteEntity.name,
							externalSiteSellerEntity.sellerName,
							externalProductGroupEntity.sellerId,
							externalProductGroupEntity.productGroupId,
							externalProductGroupEntity.brandId,
							externalBrandEntity.externalBrandId,
							externalProductGroupEntity.categoryId,
							externalCategoryEntity.externalCategoryId,
							externalProductGroupEntity.externalProductGroupId,
							externalProductGroupEntity.productName,
							externalProductGroupEntity.regularPrice,
							externalProductGroupEntity.currentPrice,
							externalProductGroupEntity.status,
							externalProductGroupEntity.fixedPrice,
							externalProductGroupEntity.soldOut,
							externalProductGroupEntity.displayed
						)
					)
				);
	}

	private List<Long> fetchExternalProductGroupIds(List<Long> siteIds, List<Long> productGroupIds, SyncStatus status) {
		return queryFactory
			.select(externalProductGroupEntity.id)
			.from(externalProductGroupEntity)
			.where(
				siteIdIn(siteIds), productGroupIdIn(productGroupIds), statusEq(status)
			)
			.limit(1)
			.orderBy(externalProductGroupEntity.productGroupId.desc())
			.fetch();
	}


	private BooleanExpression siteIdEq(long siteId) {
		return externalProductGroupEntity.siteId.eq(siteId);
	}

	private BooleanExpression siteIdIn(List<Long> siteIds) {
		if(siteIds == null || siteIds.isEmpty()) return null;
		return externalProductGroupEntity.siteId.in(siteIds);
	}

	private BooleanExpression productGroupIdEq(long productGroupId) {
		return externalProductGroupEntity.productGroupId.eq(productGroupId);
	}

	private BooleanExpression productGroupIdIn(List<Long> productGroupIds) {
		if(productGroupIds == null || productGroupIds.isEmpty()) return null;
		return externalProductGroupEntity.productGroupId.in(productGroupIds);
	}

	private BooleanExpression statusEq(SyncStatus status) {
		if(status != null) return externalProductGroupEntity.status.eq(status);
		return null;
	}

	private BooleanExpression externalProductGroupIdIn(List<Long> externalProductGroupIds) {
		return externalProductGroupEntity.id.in(externalProductGroupIds);
	}

}
