package com.ryuqq.core.events;

/**
 * 상품 등록 시
 * 외부몰 도메인에 외부몰 상품 그룹 등록을 위한 이벤트 발행 메세지
 * @param sellerId 판매자 아이디
 * @param productGroupId 상품 그룹 아이디
 */

public record ProductGroupSyncRequiredEvent(
	long sellerId,
	long productGroupId,
	long brandId,
	long categoryId
) {}
