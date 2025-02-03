package com.ryuqq.core.storage.db.external;

import static com.ryuqq.core.storage.db.external.QExternalProductGroupEntity.externalProductGroupEntity;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.enums.SyncStatus;

@Repository
public class ExternalProductGroupStatusUpdateQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public ExternalProductGroupStatusUpdateQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public long updateExternalProductGroupStatus(Long productGroupId, long siteId, String externalProductGroupId, SyncStatus syncStatus){
		if(syncStatus.isApproved()){
			return updateApproved(productGroupId, siteId, externalProductGroupId, syncStatus);
		}
		return updateFailed(siteId, externalProductGroupId, syncStatus);
	}

	private long updateApproved(Long productGroupId, long siteId, String externalProductGroupId, SyncStatus syncStatus){
		return queryFactory.update(externalProductGroupEntity)
			.set(externalProductGroupEntity.externalProductGroupId, externalProductGroupId)
			.set(externalProductGroupEntity.status, syncStatus)
			.where(externalProductGroupEntity.productGroupId.eq(productGroupId), externalProductGroupEntity.siteId.eq(siteId))
			.execute();
	}

	private long updateFailed(long siteId, String externalProductGroupId, SyncStatus syncStatus){
		return queryFactory.update(externalProductGroupEntity)
			.set(externalProductGroupEntity.status, syncStatus)
			.where(externalProductGroupEntity.externalProductGroupId.eq(externalProductGroupId), externalProductGroupEntity.siteId.eq(siteId))
			.execute();
	}


}
