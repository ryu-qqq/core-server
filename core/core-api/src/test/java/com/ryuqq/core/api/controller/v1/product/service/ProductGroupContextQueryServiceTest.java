package com.ryuqq.core.api.controller.v1.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupContextQueryServiceTest extends BaseUnitTest {

	@Mock
	private ProductGroupContextQueryInterface productGroupContextQueryInterface;

	@InjectMocks
	private ProductGroupContextQueryService productGroupContextQueryService;

	@Nested
	@DisplayName("fetchById()는")
	class FetchByIdTests {

		@Test
		@DisplayName("존재하는 productGroupId로 조회 시, 해당 ProductGroupContext를 반환해야 한다.")
		void shouldReturnProductGroupContextWhenIdExists() {
			// Given
			long productGroupId = 1L;
			ProductGroupContext expectedContext = mock(ProductGroupContext.class);
			when(productGroupContextQueryInterface.fetchById(productGroupId)).thenReturn(expectedContext);

			// When
			ProductGroupContext actualContext = productGroupContextQueryService.fetchById(productGroupId);

			// Then
			assertNotNull(actualContext);
			assertEquals(expectedContext, actualContext);
		}

		@Test
		@DisplayName("존재하지 않는 productGroupId로 조회 시, 예외가 발생해야 한다.")
		void shouldThrowExceptionWhenIdDoesNotExist() {
			// Given
			long productGroupId = 999L;
			when(productGroupContextQueryInterface.fetchById(productGroupId)).thenThrow(new IllegalArgumentException("ProductGroup not found"));

			// When & Then
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				productGroupContextQueryService.fetchById(productGroupId);
			});

			assertEquals("ProductGroup not found", exception.getMessage());
		}
	}

	@Nested
	@DisplayName("fetchByCondition()은")
	class FetchByConditionTests {

		@Test
		@DisplayName("조건에 맞는 ProductGroupContext 리스트를 반환해야 한다.")
		void shouldReturnProductGroupContextsWhenConditionMatches() {
			// Given
			ProductGroupSearchCondition requestDto = mock(ProductGroupSearchCondition.class);
			List<ProductGroupContext> expectedList = List.of(mock(ProductGroupContext.class), mock(ProductGroupContext.class));

			when(productGroupContextQueryInterface.fetchByCondition(any()))
				.thenAnswer(invocation -> expectedList);

			// When
			List<? extends ProductGroupContext> actualList = productGroupContextQueryService.fetchByCondition(requestDto);

			// Then
			assertNotNull(actualList);
			assertFalse(actualList.isEmpty());
			assertEquals(expectedList.size(), actualList.size());
		}

		@Test
		@DisplayName("조건에 맞는 데이터가 없을 경우 빈 리스트를 반환해야 한다.")
		void shouldReturnEmptyListWhenNoDataMatches() {
			// Given
			ProductGroupSearchCondition searchCondition = mock(ProductGroupSearchCondition.class);

			when(productGroupContextQueryInterface.fetchByCondition(searchCondition)).thenReturn(Collections.emptyList());

			// When
			List<? extends ProductGroupContext> actualList = productGroupContextQueryService.fetchByCondition(searchCondition);

			// Then
			assertNotNull(actualList);
			assertTrue(actualList.isEmpty());
		}
	}
}
