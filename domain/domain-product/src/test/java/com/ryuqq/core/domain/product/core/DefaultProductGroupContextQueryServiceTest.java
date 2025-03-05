package com.ryuqq.core.domain.product.core;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.ProductGroupContextFinder;
import com.ryuqq.core.unit.test.BaseUnitTest;

class DefaultProductGroupContextQueryServiceTest extends BaseUnitTest {

	@Mock
	private ProductGroupContextFinder productGroupContextFinder;

	@InjectMocks
	private DefaultProductGroupContextQueryService queryService;

	@Nested
	@DisplayName("fetchById() 메서드는")
	class FetchByIdTests {

		@Test
		@DisplayName("존재하는 productGroupId에 대해 적절한 ProductGroupContext를 반환해야 한다.")
		void shouldReturnProductGroupContextWhenIdExists() {
			// Given
			long productGroupId = 1L;
			ProductGroupContext expectedContext = mock(ProductGroupContext.class);
			when(productGroupContextFinder.fetchById(productGroupId)).thenReturn(expectedContext);

			// When
			ProductGroupContext actualContext = queryService.fetchById(productGroupId);

			// Then
			assertNotNull(actualContext);
			assertEquals(expectedContext, actualContext);
		}

		@Test
		@DisplayName("존재하지 않는 productGroupId에 대해 null을 반환해야 한다.")
		void shouldReturnNullWhenIdDoesNotExist() {
			// Given
			long productGroupId = 999L;
			when(productGroupContextFinder.fetchById(productGroupId)).thenReturn(null);

			// When
			ProductGroupContext actualContext = queryService.fetchById(productGroupId);

			// Then
			assertNull(actualContext);
		}
	}

	@Nested
	@DisplayName("fetchByCondition() 메서드는")
	class FetchByConditionTests {

		@Test
		@DisplayName("적절한 검색 조건이 주어지면 ProductGroupContext 리스트를 반환해야 한다.")
		void shouldReturnProductGroupContextListWhenConditionIsValid() {
			// Given
			ProductGroupSearchCondition searchCondition = mock(ProductGroupSearchCondition.class);
			List<ProductGroupContext> expectedList = List.of(mock(ProductGroupContext.class), mock(ProductGroupContext.class));
			when(productGroupContextFinder.fetchByCondition(searchCondition))
				.thenAnswer(invocation -> expectedList);


			// When
			List<? extends ProductGroupContext> actualList = queryService.fetchByCondition(searchCondition);

			// Then
			assertNotNull(actualList);
			assertEquals(2, actualList.size());
			assertEquals(expectedList, actualList);
		}

		@Test
		@DisplayName("검색 조건에 해당하는 결과가 없으면 빈 리스트를 반환해야 한다.")
		void shouldReturnEmptyListWhenNoResultsMatchCondition() {
			// Given
			ProductGroupSearchCondition searchCondition = mock(ProductGroupSearchCondition.class);
			when(productGroupContextFinder.fetchByCondition(searchCondition)).thenReturn(List.of());

			// When
			List<? extends ProductGroupContext> actualList = queryService.fetchByCondition(searchCondition);

			// Then
			assertNotNull(actualList);
			assertTrue(actualList.isEmpty());
		}
	}
}
