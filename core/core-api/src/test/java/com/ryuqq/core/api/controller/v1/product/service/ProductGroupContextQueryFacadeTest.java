package com.ryuqq.core.api.controller.v1.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextResponseMapper;
import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextResponseMapperProvider;
import com.ryuqq.core.api.controller.v1.product.request.ProductGroupSearchConditionRequestDto;
import com.ryuqq.core.api.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.enums.RequesterType;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductGroupContextQueryFacade 단위 테스트")
class ProductGroupContextQueryFacadeTest extends BaseUnitTest {

	@Mock
	private ProductGroupContextQueryService productGroupContextQueryService;

	@Mock
	private ProductGroupRelatedQueryService productGroupRelatedQueryService;

	@Mock
	private ProductGroupContextResponseMapperProvider productGroupContextResponseMapperProvider;

	@Mock
	private ProductGroupContextResponseMapper productGroupContextResponseMapper;

	@InjectMocks
	private ProductGroupContextQueryFacade productGroupContextQueryFacade;

	@Nested
	@DisplayName("fetchByIdForRequester()는")
	class FetchByIdForRequester {

		@Test
		@DisplayName("존재하는 ID와 요청자 유형이 주어지면 ProductGroupContextResponse를 반환해야 한다.")
		void shouldReturnProductGroupContextResponseIfExists() {
			// Given
			Long productGroupId = 1L;
			RequesterType requesterType = RequesterType.DEFAULT;

			ProductGroupContext mockContext = mock(ProductGroupContext.class);
			ProductGroup mockProductGroup = mock(ProductGroup.class);
			Brand mockBrand = mock(Brand.class);
			List<Category> mockCategories = List.of(mock(Category.class));
			Seller mockSeller = mock(Seller.class);
			ProductGroupContextResponse expectedResponse = mock(ProductGroupContextResponse.class);

			when(mockContext.getProductGroup()).thenReturn(mockProductGroup);
			when(mockProductGroup.getBrandId()).thenReturn(10L);
			when(mockProductGroup.getCategoryId()).thenReturn(20L);
			when(mockProductGroup.getSellerId()).thenReturn(30L);

			when(productGroupContextQueryService.fetchById(productGroupId)).thenReturn(mockContext);
			when(productGroupRelatedQueryService.fetchBrandById(10L)).thenReturn(mockBrand);
			when(productGroupRelatedQueryService.fetchCategoriesById(20L))
				.thenAnswer(invocationOnMock -> mockCategories);
			when(productGroupRelatedQueryService.fetchSellerById(30L)).thenReturn(mockSeller);

			when(productGroupContextResponseMapperProvider.getMapper(requesterType)).thenReturn(productGroupContextResponseMapper);
			when(productGroupContextResponseMapper.toResponseDto(mockContext, mockBrand, mockCategories, mockSeller)).thenReturn(expectedResponse);

			// When
			ProductGroupContextResponse result = productGroupContextQueryFacade.fetchByIdForRequester(productGroupId, requesterType);

			// Then
			assertNotNull(result);
			assertEquals(expectedResponse, result);
		}
	}

	@Nested
	@DisplayName("fetchByConditionForRequester()는")
	class FetchByConditionForRequester {

		@Test
		@DisplayName("검색 결과가 없으면 빈 리스트를 반환해야 한다.")
		void shouldReturnEmptyListIfNoResults() {
			// Given
			ProductGroupSearchConditionRequestDto requestDto = mock(ProductGroupSearchConditionRequestDto.class);
			ProductGroupSearchCondition condition = mock(ProductGroupSearchCondition.class);
			RequesterType requesterType = RequesterType.DEFAULT;

			when(requestDto.toProductGroupSearchCondition()).thenReturn(condition);
			when(productGroupContextQueryService.countByCondition(condition)).thenReturn(0L);

			// When
			List<?> result = productGroupContextQueryFacade.fetchByConditionForRequester(requestDto, requesterType);

			// Then
			assertNotNull(result);
			assertTrue(result.isEmpty());
		}


		@Test
		@DisplayName("검색 결과가 존재하면 변환된 응답을 반환해야 한다.")
		void shouldReturnProductGroupContextResponseListIfExists() {
			// Given
			ProductGroupSearchConditionRequestDto requestDto = mock(ProductGroupSearchConditionRequestDto.class);
			ProductGroupSearchCondition condition = mock(ProductGroupSearchCondition.class);
			RequesterType requesterType = RequesterType.ADMIN;

			List<ProductGroupContext> mockContexts = List.of(mock(ProductGroupContext.class));
			Map<String, List<Long>> extractedIds = Map.of(
				"brandIds", List.of(10L),
				"categoryIds", List.of(20L),
				"sellerIds", List.of(30L)
			);

			Map<Long, Brand> brandMap = Map.of(10L, mock(Brand.class));
			Map<Long, List<Category>> categoryMap = Map.of(20L, List.of(mock(Category.class)));
			Map<Long, Seller> sellerMap = Map.of(30L, mock(Seller.class));

			Map<String, Object> relatedEntities = Map.of(
				"brandMap", brandMap,
				"categoryMap", categoryMap,
				"sellerMap", sellerMap
			);

			when(requestDto.toProductGroupSearchCondition()).thenReturn(condition);
			when(productGroupContextQueryService.countByCondition(condition)).thenReturn(5L);
			when(productGroupContextQueryService.fetchByCondition(condition)).thenAnswer(invocationOnMock -> mockContexts);

			try (MockedStatic<ProductGroupExtractor> mockedStatic = mockStatic(ProductGroupExtractor.class)) {
				mockedStatic.when(() -> ProductGroupExtractor.extractIds(mockContexts)).thenReturn(extractedIds);
				mockedStatic.when(() -> ProductGroupExtractor.fetchRelatedEntities(productGroupRelatedQueryService, extractedIds)).thenReturn(relatedEntities);

				when(productGroupContextResponseMapperProvider.getMapper(requesterType)).thenReturn(productGroupContextResponseMapper);

				when(productGroupContextResponseMapper.toResponseDto(
					anyList(),
					anyMap(),
					anyMap(),
					anyMap(),
					anyInt(),
					anyLong()
				)).thenReturn(mock(List.class));

				// When
				Object result = productGroupContextQueryFacade.fetchByConditionForRequester(requestDto, requesterType);

				// Then
				assertNotNull(result);
			}
		}
	}

}
