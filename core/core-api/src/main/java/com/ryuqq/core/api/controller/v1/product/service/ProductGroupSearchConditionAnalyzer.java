package com.ryuqq.core.api.controller.v1.product.service;

import org.springframework.util.CollectionUtils;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupSearchConditionRequestDto;
import com.ryuqq.core.enums.ProductSortField;

public class ProductGroupSearchConditionAnalyzer {

	public static boolean isSimpleQuery(ProductGroupSearchConditionRequestDto condition) {
		return isCursorOnly(condition) ||
			isPageOnly(condition) ||
			isSimpleBooleanFilter(condition) ||
			isSimpleSortWithoutSearch(condition) ||
			isManagementTypeOnly(condition) ||
			isSellerIdOnly(condition) ||
			isBrandIdOnly(condition);
	}

	/**  cursorId만 존재 (No-Offset 페이징 가능) */
	private static boolean isCursorOnly(ProductGroupSearchConditionRequestDto condition) {
		return condition.cursorId() != null &&
			CollectionUtils.isEmpty(condition.sellerIds()) &&
			CollectionUtils.isEmpty(condition.categoryIds()) &&
			CollectionUtils.isEmpty(condition.brandIds()) &&
			condition.searchWord() == null &&
			condition.minSalePrice() == null &&
			condition.maxSalePrice() == null;
	}

	/**  page, size만 존재 (OFFSET 페이징 가능) */
	private static boolean isPageOnly(ProductGroupSearchConditionRequestDto condition) {
		return condition.cursorId() == null &&
			condition.page() != null && condition.size() != null &&
			CollectionUtils.isEmpty(condition.sellerIds()) &&
			CollectionUtils.isEmpty(condition.categoryIds()) &&
			CollectionUtils.isEmpty(condition.brandIds()) &&
			condition.searchWord() == null &&
			condition.minSalePrice() == null &&
			condition.maxSalePrice() == null;
	}

	/** displayed, soldOut만 필터링 */
	private static boolean isSimpleBooleanFilter(ProductGroupSearchConditionRequestDto condition) {
		return condition.cursorId() == null &&
			condition.page() == null &&
			CollectionUtils.isEmpty(condition.sellerIds()) &&
			CollectionUtils.isEmpty(condition.categoryIds()) &&
			CollectionUtils.isEmpty(condition.brandIds()) &&
			condition.productSortField() == null &&
			condition.searchWord() == null &&
			condition.minSalePrice() == null &&
			condition.maxSalePrice() == null;
	}

	/** ✅ 정렬 방식이 단순하면서 검색어가 없는 경우만 심플쿼리로 인정 */
	private static boolean isSimpleSortWithoutSearch(ProductGroupSearchConditionRequestDto condition) {
		return (condition.productSortField() == null || condition.productSortField().equals(ProductSortField.ID)) &&
			(condition.searchWord() == null || condition.searchWord().isBlank());
	}


	/**  managementType만 필터링 */
	private static boolean isManagementTypeOnly(ProductGroupSearchConditionRequestDto condition) {
		return condition.managementType() != null &&
			CollectionUtils.isEmpty(condition.sellerIds()) &&
			CollectionUtils.isEmpty(condition.categoryIds()) &&
			CollectionUtils.isEmpty(condition.brandIds()) &&
			condition.productSortField() == null &&
			condition.searchWord() == null &&
			condition.minSalePrice() == null &&
			condition.maxSalePrice() == null;
	}

	/**  sellerId 단일 조회 */
	private static boolean isSellerIdOnly(ProductGroupSearchConditionRequestDto condition) {
		return !CollectionUtils.isEmpty(condition.sellerIds()) &&
			condition.sellerIds().size() == 1 &&
			CollectionUtils.isEmpty(condition.categoryIds()) &&
			CollectionUtils.isEmpty(condition.brandIds()) &&
			condition.productSortField() == null &&
			condition.searchWord() == null &&
			condition.minSalePrice() == null &&
			condition.maxSalePrice() == null;
	}

	/**  brandId 단일 조회 */
	private static boolean isBrandIdOnly(ProductGroupSearchConditionRequestDto condition) {
		return !CollectionUtils.isEmpty(condition.brandIds()) &&
			condition.brandIds().size() == 1 &&
			CollectionUtils.isEmpty(condition.categoryIds()) &&
			CollectionUtils.isEmpty(condition.sellerIds()) &&
			condition.productSortField() == null &&
			condition.searchWord() == null &&
			condition.minSalePrice() == null &&
			condition.maxSalePrice() == null;
	}
}
