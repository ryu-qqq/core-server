package com.ryuqq.core.events;

import com.ryuqq.core.enums.ProductDomainEventType;

/**
 * 상품 업데이트 시
 * 외부몰 도메인에 외부몰 상품을 즉각적으로 업데이트해야하는 이벤트
 * @param sellerId 판매자 아이디
 * @param productGroupId 상품 그룹 아이디
 * @param productDomainEventType (가격, 재고)
 */
public record RealTimeUpdateEvent(
	long sellerId,
	long productGroupId,
	ProductDomainEventType productDomainEventType
) {}
