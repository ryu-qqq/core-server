package com.ryuqq.core.storage.db.product.group;

import static com.ryuqq.core.storage.db.product.delivery.QProductDeliveryEntity.productDeliveryEntity;
import static com.ryuqq.core.storage.db.product.group.QProductGroupEntity.productGroupEntity;
import static com.ryuqq.core.storage.db.product.image.QProductDetailDescriptionEntity.productDetailDescriptionEntity;
import static com.ryuqq.core.storage.db.product.image.QProductGroupImageEntity.productGroupImageEntity;
import static com.ryuqq.core.storage.db.product.notice.QProductNoticeEntity.productNoticeEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.ProductStatus;
import com.ryuqq.core.storage.db.product.delivery.QProductDeliveryDto;
import com.ryuqq.core.storage.db.product.image.QProductDetailDescriptionDto;
import com.ryuqq.core.storage.db.product.image.QProductGroupImageDto;
import com.ryuqq.core.storage.db.product.notice.QProductNoticeDto;

@Repository
public class ProductGroupQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public ProductGroupQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;

	}

	public Optional<ProductGroupContextDto> fetchContextById(long productGroupId) {
		return Optional.ofNullable(queryFactory
			.from(productGroupEntity)
			.innerJoin(productNoticeEntity)
			.on(productNoticeEntity.productGroupId.eq(productGroupEntity.id))
			.innerJoin(productDeliveryEntity)
			.on(productDeliveryEntity.productGroupId.eq(productGroupEntity.id))
			.leftJoin(productDetailDescriptionEntity)
			.on(productDetailDescriptionEntity.productGroupId.eq(productGroupEntity.id))
			.leftJoin(productGroupImageEntity)
			.on(productGroupImageEntity.productGroupId.eq(productGroupEntity.id))
			.on(productGroupImageEntity.deleted.eq(false))
			.where(
				productGroupIdIn(List.of(productGroupId))
			)
			.transform(
				GroupBy.groupBy(productGroupEntity.id).as(
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
							productGroupEntity.discountRate,
							productGroupEntity.soldOut,
							productGroupEntity.displayed,
							productGroupEntity.productStatus,
							productGroupEntity.keywords.coalesce("")
						),
						new QProductNoticeDto(
							productNoticeEntity.productGroupId,
							productNoticeEntity.material,
							productNoticeEntity.color,
							productNoticeEntity.size,
							productNoticeEntity.maker,
							productNoticeEntity.origin,
							productNoticeEntity.washingMethod,
							productNoticeEntity.yearMonth,
							productNoticeEntity.assuranceStandard,
							productNoticeEntity.asPhone
							),
						new QProductDeliveryDto(
							productDeliveryEntity.productGroupId,
							productDeliveryEntity.deliveryArea,
							productDeliveryEntity.deliveryFee,
							productDeliveryEntity.deliveryPeriodAverage,
							productDeliveryEntity.returnMethodDomestic,
							productDeliveryEntity.returnCourierDomestic,
							productDeliveryEntity.returnChargeDomestic,
							productDeliveryEntity.returnExchangeAreaDomestic
						),
						GroupBy.list(
							new QProductGroupImageDto(
								productGroupImageEntity.id,
								productGroupImageEntity.productGroupId,
								productGroupImageEntity.productImageType,
								productGroupImageEntity.imageUrl.coalesce(""),
								productGroupImageEntity.originUrl.coalesce(""),
								productGroupImageEntity.deleted
							)
						),
						new QProductDetailDescriptionDto(
							productDetailDescriptionEntity.productGroupId,
							productDetailDescriptionEntity.detailDescription.coalesce("")
						)
					)
				)
			).get(productGroupId));
	}

	public List<ProductGroupContextDto> fetchContextByIds(List<Long> productGroupIds) {
		return queryFactory
			.from(productGroupEntity)
			.innerJoin(productNoticeEntity)
			.on(productNoticeEntity.productGroupId.eq(productGroupEntity.id))
			.innerJoin(productDeliveryEntity)
			.on(productDeliveryEntity.productGroupId.eq(productGroupEntity.id))
			.leftJoin(productDetailDescriptionEntity)
			.on(productDetailDescriptionEntity.productGroupId.eq(productGroupEntity.id))
			.leftJoin(productGroupImageEntity)
			.on(productGroupImageEntity.productGroupId.eq(productGroupEntity.id))
			.on(productGroupImageEntity.deleted.eq(false))
			.where(
				productGroupIdIn(productGroupIds)
			)
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
							productGroupEntity.discountRate,
							productGroupEntity.soldOut,
							productGroupEntity.displayed,
							productGroupEntity.productStatus,
							productGroupEntity.keywords.coalesce("")
						),
						new QProductNoticeDto(
 							productNoticeEntity.productGroupId,
							productNoticeEntity.material,
							productNoticeEntity.color,
							productNoticeEntity.size,
							productNoticeEntity.maker,
							productNoticeEntity.origin,
							productNoticeEntity.washingMethod,
							productNoticeEntity.yearMonth,
							productNoticeEntity.assuranceStandard,
							productNoticeEntity.asPhone
						),
						new QProductDeliveryDto(
   							productDeliveryEntity.productGroupId,
							productDeliveryEntity.deliveryArea,
							productDeliveryEntity.deliveryFee,
							productDeliveryEntity.deliveryPeriodAverage,
							productDeliveryEntity.returnMethodDomestic,
							productDeliveryEntity.returnCourierDomestic,
							productDeliveryEntity.returnChargeDomestic,
							productDeliveryEntity.returnExchangeAreaDomestic
						),
						GroupBy.list(
							new QProductGroupImageDto(
								productGroupImageEntity.id,
								productGroupImageEntity.productGroupId,
								productGroupImageEntity.productImageType,
								productGroupImageEntity.imageUrl.coalesce(""),
								productGroupImageEntity.originUrl.coalesce(""),
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

	public List<ProductGroupDto> fetchBySellerId(long sellerId){
		return queryFactory
			.select(
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
					productGroupEntity.discountRate,
					productGroupEntity.soldOut,
					productGroupEntity.displayed,
					productGroupEntity.productStatus,
					productGroupEntity.keywords.coalesce("")
				)
			)
			.from(productGroupEntity)
			.where(
				sellerIdEq(sellerId)
			).fetch();
	}

	private BooleanExpression productGroupIdIn(List<Long> productGroupIds) {
		if(!productGroupIds.isEmpty()) return productGroupEntity.id.in(productGroupIds);
		else return null;
	}

	private BooleanExpression isProductGroupIdGt(Long productGroupId){
		if(productGroupId !=null) return productGroupEntity.id.gt(productGroupId);
		else return null;
	}
	private BooleanExpression isProductGroupIdLt(Long productGroupId){
		if(productGroupId !=null) return productGroupEntity.id.lt(productGroupId);
		else return null;
	}

	private BooleanExpression productStatusEq(ProductStatus productStatus){
		if(productStatus != null) return productGroupEntity.productStatus.eq(productStatus);
		else return null;
	}

	private BooleanExpression soldOutEq(Boolean soldOutYn) {
		if(soldOutYn == null) return null;
		return productGroupEntity.soldOut.eq(soldOutYn);
	}

	private BooleanExpression displayEq(Boolean displayYn) {
		if(displayYn == null) return null;
		return productGroupEntity.displayed.eq(displayYn);
	}

	private BooleanExpression betweenTime(LocalDateTime start, LocalDateTime end){
		if(start == null || end == null) return null;
		return productGroupEntity.createdAt.between(start, end);
	}

	private BooleanExpression managementTypeEq(ManagementType managementType) {
		return managementType !=null ? productGroupEntity.managementType.eq(managementType) : null;
	}

	private BooleanExpression betweenSalePercent(Long minDiscountRate, Long maxDiscountRate) {
		if(minDiscountRate!= null && maxDiscountRate != null && minDiscountRate >=0 && maxDiscountRate >0){
			return productGroupEntity.discountRate.between(minDiscountRate, maxDiscountRate);
		}
		return null;
	}

	private BooleanExpression sellerIdEq(Long sellerId){
		if(sellerId != null) return productGroupEntity.sellerId.eq(sellerId);
		else return null;
	}

	private BooleanExpression styleCodeEq(String styleCode){
		if(styleCode != null && !styleCode.isBlank()) return productGroupEntity.styleCode.eq(styleCode);
		else return null;
	}


	private BooleanExpression categoryIn(List<Long> categoryIds) {
		if(!categoryIds.isEmpty()){
			return productGroupEntity.categoryId.in(categoryIds);
		}
		return null;
	}

	private BooleanExpression brandIdIn(List<Long> brandIds) {
		if(!brandIds.isEmpty()){
			return productGroupEntity.brandId.in(brandIds);
		}
		return null;
	}

	private BooleanExpression betweenPrice(Long minSalePrice, Long maxSalePrice) {
		if (minSalePrice != null && maxSalePrice != null && minSalePrice >= 0 && maxSalePrice > 0) {
			BigDecimal minPrice = BigDecimal.valueOf(minSalePrice);
			BigDecimal maxPrice = BigDecimal.valueOf(maxSalePrice);
			return productGroupEntity.currentPrice.between(minPrice, maxPrice);
		}

		return null;
	}


	private BooleanExpression productGroupNameLike(String productGroupName) {
		if(productGroupName != null && !productGroupName.isBlank()){
			return productGroupEntity.productGroupName.like("%"+productGroupName+"%");
		}
		return null;
	}



}
