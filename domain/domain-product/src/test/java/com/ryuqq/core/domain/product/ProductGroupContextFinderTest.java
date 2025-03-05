package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupContextFinderTest extends BaseUnitTest {

	@Mock
	private ProductGroupContextAssembler productGroupContextAssembler;

	@InjectMocks
	private ProductGroupContextFinder productGroupContextFinder;

	@Nested
	@DisplayName("fetchById()는")
	class FetchByIdTests {

		@Test
		@DisplayName("존재하는 productGroupId로 조회 시, 해당 ProductGroupContext를 반환해야 한다.")
		void shouldReturnProductGroupContextWhenIdExists() {
			// Given
			long productGroupId = 1L;
			ProductGroupContext expectedContext = mock(ProductGroupContext.class);

			when(productGroupContextAssembler.assemble(productGroupId))
				.thenAnswer(invocation -> expectedContext);

			// When
			ProductGroupContext actualContext = productGroupContextFinder.fetchById(productGroupId);

			// Then
			assertNotNull(actualContext);
			assertEquals(expectedContext, actualContext);
		}

		@Test
		@DisplayName("존재하지 않는 productGroupId로 조회 시, 예외가 발생해야 한다.")
		void shouldThrowExceptionWhenIdDoesNotExist() {
			// Given
			long productGroupId = 999L;
			when(productGroupContextAssembler.assemble(productGroupId)).thenThrow(new IllegalArgumentException("ProductGroup not found"));

			// When & Then
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				productGroupContextFinder.fetchById(productGroupId);
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
			ProductGroupSearchCondition searchCondition = mock(ProductGroupSearchCondition.class);
			List<ProductGroupContext> expectedList = List.of(mock(ProductGroupContext.class), mock(ProductGroupContext.class));

			when(productGroupContextAssembler.assemble(searchCondition)).thenAnswer(invocation -> expectedList);

			// When
			List<? extends ProductGroupContext> actualList = productGroupContextFinder.fetchByCondition(searchCondition);

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

			when(productGroupContextAssembler.assemble(searchCondition)).thenAnswer(invocation -> List.of()); // ✅ 빈 리스트 반환

			// When
			List<? extends ProductGroupContext> actualList = productGroupContextFinder.fetchByCondition(searchCondition);

			// Then
			assertNotNull(actualList);
			assertTrue(actualList.isEmpty());
		}
	}
}
