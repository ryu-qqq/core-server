package com.ryuqq.core.storage.db.external;

import static com.ryuqq.core.storage.db.external.QExternalSiteSellerEntity.externalSiteSellerEntity;
import static com.ryuqq.core.storage.db.external.QSiteEntity.siteEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.enums.SiteType;
import com.ryuqq.core.storage.db.external.dto.ExternalSiteDto;
import com.ryuqq.core.storage.db.external.dto.ExternalSiteSellerRelationDto;
import com.ryuqq.core.storage.db.external.dto.QExternalSiteDto;
import com.ryuqq.core.storage.db.external.dto.QExternalSiteSellerRelationDto;

@Repository
public class ExternalSiteQueryDslRepository  {

	private final JPAQueryFactory queryFactory;

	public ExternalSiteQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public Optional<ExternalSiteSellerRelationDto> fetchBySellerId(long sellerId) {
		return Optional.ofNullable(queryFactory
			.from(externalSiteSellerEntity)
			.innerJoin(siteEntity)
				.on(externalSiteSellerEntity.siteId.eq(siteEntity.id))

			.where(
				sellerIdEq(sellerId),
				activeStatusEq(),
				syncSiteType()
			).transform(
				GroupBy.groupBy(externalSiteSellerEntity.sellerId).as(
					new QExternalSiteSellerRelationDto(
						externalSiteSellerEntity.sellerId,
						externalSiteSellerEntity.sellerName,
						GroupBy.list(
							new QExternalSiteDto(
								externalSiteSellerEntity.siteId,
								siteEntity.name
							)
						)
					)
				)
			).get(sellerId));
	}

	public List<ExternalSiteSellerRelationDto> fetchExternalSiteSellerRelation(){
		return queryFactory
			.from(externalSiteSellerEntity)
			.innerJoin(siteEntity)
			.on(externalSiteSellerEntity.siteId.eq(siteEntity.id))
			.where(
				activeStatusEq(),
				syncSiteType()
			).transform(
				GroupBy.groupBy(externalSiteSellerEntity.sellerId).list(
					new QExternalSiteSellerRelationDto(
						externalSiteSellerEntity.sellerId,
						externalSiteSellerEntity.sellerName,
						GroupBy.list(
							new QExternalSiteDto(
								externalSiteSellerEntity.siteId,
								siteEntity.name
							)
						)
					)
				)
			);
	}

	public Optional<ExternalSiteDto> fetchBySiteName(SiteName siteName){
		return Optional.ofNullable(queryFactory
				.select(
					new QExternalSiteDto(
						externalSiteSellerEntity.siteId,
						siteEntity.name
					)
				)
			.from(siteEntity)
			.where(siteNameEq(siteName))
			.fetchOne()
		);
	}

	private BooleanExpression sellerIdEq(long sellerId){
		return externalSiteSellerEntity.sellerId.eq(sellerId);
	}

	private BooleanExpression activeStatusEq(){
		return externalSiteSellerEntity.activeStatus.eq(true);
	}

	private BooleanExpression syncSiteType(){
		return siteEntity.siteType.eq(SiteType.SYNC);
	}

	private BooleanExpression siteNameEq(SiteName siteName){
		return siteEntity.name.eq(siteName);
	}

}
