package com.ryuqq.core.api.controller.v1.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductGroupExtractor 단위 테스트")
class ProductGroupExtractorTest extends BaseUnitTest {

	// @Test
	// @DisplayName("ProductGroupContext 리스트에서 ID들을 올바르게 추출해야 한다.")
	// void shouldExtractIdsCorrectly() {
	// 	// Given
	// 	ProductGroupContext context1 = new ProductGroupContext(productGroup1);
	// 	ProductGroupContext context2 = new ProductGroupContext(productGroup2);
	//
	// 	List<ProductGroupContext> contexts = List.of(context1, context2);
	//
	// 	// When
	// 	Map<String, List<Long>> result = ProductGroupExtractor.extractIds(contexts);
	//
	// 	// Then
	// 	assertEquals(List.of(101L, 102L), result.get("brandIds"));
	// 	assertEquals(List.of(201L, 202L), result.get("categoryIds"));
	// 	assertEquals(List.of(301L, 302L), result.get("sellerIds"));
	// }

	@Test
	@DisplayName("ID 목록으로부터 브랜드, 카테고리, 셀러 맵을 올바르게 생성해야 한다.")
	void shouldFetchRelatedEntitiesCorrectly() {
		// Given
		ProductGroupRelatedQueryService productGroupRelatedQueryService = mock(ProductGroupRelatedQueryService.class);

		Map<String, List<Long>> extractedIds = Map.of(
			"brandIds", List.of(10L),
			"categoryIds", List.of(20L),
			"sellerIds", List.of(30L)
		);

		Brand mockBrand = mock(Brand.class);
		List<Category> mockCategories = List.of(mock(Category.class));
		Seller mockSeller = mock(Seller.class);

		when(productGroupRelatedQueryService.fetchBrandById(List.of(10L))).thenReturn(Map.of(10L, mockBrand));
		when(productGroupRelatedQueryService.fetchCategoryHierarchyByIds(List.of(20L))).thenReturn(Map.of(20L, mockCategories));
		when(productGroupRelatedQueryService.fetchSellerById(List.of(30L))).thenReturn(Map.of(30L, mockSeller));

		// When
		Map<String, Object> result = ProductGroupExtractor.fetchRelatedEntities(productGroupRelatedQueryService, extractedIds);

		// Then
		assertEquals(1, ((Map<Long, Brand>) result.get("brandMap")).size());
		assertEquals(1, ((Map<Long, List<Category>>) result.get("categoryMap")).size());
		assertEquals(1, ((Map<Long, Seller>) result.get("sellerMap")).size());
	}


}
