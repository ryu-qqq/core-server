package com.ryuqq.core.api.controller.v1.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.brand.core.BrandSearchCondition;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.category.core.CategoryQueryInterface;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.domain.seller.core.SellerQueryInterface;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductGroupRelatedQueryService 단위 테스트")
class ProductGroupRelatedQueryServiceTest extends BaseUnitTest {

	@Mock
	private BrandQueryInterface brandQueryInterface;

	@Mock
	private CategoryQueryInterface categoryQueryInterface;

	@Mock
	private SellerQueryInterface sellerQueryInterface;

	@InjectMocks
	private ProductGroupRelatedQueryService productGroupRelatedQueryService;

	@Nested
	@DisplayName("fetchBrandById()는")
	class FetchBrandById {

		@Test
		@DisplayName("브랜드 ID로 단일 브랜드를 조회해야 한다.")
		void shouldFetchSingleBrandById() {
			// Given
			long brandId = 10L;
			Brand mockBrand = mock(Brand.class);
			when(brandQueryInterface.fetchById(brandId)).thenReturn(mockBrand);

			// When
			Brand result = productGroupRelatedQueryService.fetchBrandById(brandId);

			// Then
			assertNotNull(result);
			assertEquals(mockBrand, result);
		}

		@Test
		@DisplayName("브랜드 ID 리스트로 여러 브랜드를 조회해야 한다.")
		void shouldFetchMultipleBrandsByIds() {
			// Given
			List<Long> brandIds = List.of(10L, 20L);

			Brand brand1 = mock(Brand.class);
			Brand brand2 = mock(Brand.class);
			when(brand1.id()).thenReturn(10L);
			when(brand2.id()).thenReturn(20L);

			when(brandQueryInterface.fetchByCondition(any(BrandSearchCondition.class)))
				.thenAnswer(invocation -> List.of(brand1, brand2));

			// When
			Map<Long, Brand> result = productGroupRelatedQueryService.fetchBrandById(brandIds);

			// Then
			assertNotNull(result);
			assertEquals(2, result.size());
			assertEquals(brand1, result.get(10L));
			assertEquals(brand2, result.get(20L));
		}
	}

	@Nested
	@DisplayName("fetchCategoriesById()는")
	class FetchCategoriesById {

		@Test
		@DisplayName("카테고리 ID로 단일 카테고리를 조회해야 한다.")
		void shouldFetchCategoriesById() {
			// Given
			long categoryId = 30L;
			Category mockCategory = mock(Category.class);
			when(categoryQueryInterface.fetchRecursiveByIds(categoryId, false)).thenAnswer(invocation -> List.of(mockCategory));

			// When
			List<? extends Category> result = productGroupRelatedQueryService.fetchCategoriesById(categoryId);

			// Then
			assertNotNull(result);
			assertFalse(result.isEmpty());
			assertEquals(mockCategory, result.get(0));
		}
	}

	@Nested
	@DisplayName("fetchCategoryHierarchyByIds()는")
	class FetchCategoryHierarchyByIds {

		@Test
		@DisplayName("카테고리 ID 리스트로 계층 구조를 조회해야 한다.")
		void shouldFetchCategoryHierarchyByIds() {
			// Given
			List<Long> categoryIds = List.of(40L, 50L);
			Category category1 = mock(Category.class);
			Category category2 = mock(Category.class);

			when(category1.getId()).thenReturn(40L);
			when(category2.getId()).thenReturn(50L);
			when(category1.getPath()).thenReturn("40");
			when(category2.getPath()).thenReturn("50,40");

			when(categoryQueryInterface.fetchRecursiveByIds(categoryIds, false)).thenAnswer(invocation -> List.of(category1, category2));

			// When
			Map<Long, List<Category>> result = productGroupRelatedQueryService.fetchCategoryHierarchyByIds(categoryIds);

			// Then
			assertNotNull(result);
			assertEquals(2, result.size());
			assertTrue(result.containsKey(40L));
			assertTrue(result.containsKey(50L));
		}
	}

	@Nested
	@DisplayName("fetchSellerById()는")
	class FetchSellerById {

		@Test
		@DisplayName("판매자 ID로 단일 판매자를 조회해야 한다.")
		void shouldFetchSingleSellerById() {
			// Given
			long sellerId = 60L;
			Seller mockSeller = mock(Seller.class);
			when(sellerQueryInterface.fetchById(sellerId)).thenReturn(mockSeller);

			// When
			Seller result = productGroupRelatedQueryService.fetchSellerById(sellerId);

			// Then
			assertNotNull(result);
			assertEquals(mockSeller, result);
		}

		@Test
		@DisplayName("판매자 ID 리스트로 여러 판매자를 조회해야 한다.")
		void shouldFetchMultipleSellersByIds() {
			// Given
			List<Long> sellerIds = List.of(60L, 70L);
			Seller seller1 = mock(Seller.class);
			Seller seller2 = mock(Seller.class);
			when(seller1.id()).thenReturn(60L);
			when(seller2.id()).thenReturn(70L);

			when(sellerQueryInterface.fetchByIds(sellerIds)).thenAnswer(invocation -> List.of(seller1, seller2));

			// When
			Map<Long, Seller> result = productGroupRelatedQueryService.fetchSellerById(sellerIds);

			// Then
			assertNotNull(result);
			assertEquals(2, result.size());
			assertEquals(seller1, result.get(60L));
			assertEquals(seller2, result.get(70L));
		}
	}
}
