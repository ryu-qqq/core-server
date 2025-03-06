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

@DisplayName("ProductGroupContextAssembler 테스트")
class ProductGroupContextAssemblerTest extends BaseUnitTest {

	@Mock
	private ProductGroupFinder productGroupFinder;

	@Mock
	private ProductContextAssembler productContextAssembler;

	@InjectMocks
	private ProductGroupContextAssembler productGroupContextAssembler;

	@Nested
	@DisplayName("assemble(long productGroupId)는")
	class AssembleByIdTests {

		@Test
		@DisplayName("올바른 ProductGroupContext를 조립하여 반환해야 한다.")
		void shouldAssembleProductGroupContextById() {
			// Given
			long productGroupId = 1L;
			ProductGroupContext mockProductGroupContext = mock(ProductGroupContext.class);
			ProductGroupContext assembledContext = mock(ProductGroupContext.class);

			when(productGroupFinder.fetchProductContextById(productGroupId)).thenReturn(mockProductGroupContext);
			when(productContextAssembler.assembleWithProducts(mockProductGroupContext)).thenReturn(assembledContext);

			// When
			ProductGroupContext result = productGroupContextAssembler.assemble(productGroupId);

			// Then
			assertNotNull(result);
			assertEquals(assembledContext, result);
			verify(productGroupFinder).fetchProductContextById(productGroupId);
			verify(productContextAssembler).assembleWithProducts(mockProductGroupContext);
		}
	}

	@Nested
	@DisplayName("assemble(ProductGroupSearchCondition)는")
	class AssembleByConditionTests {

		@Test
		@DisplayName("조건에 맞는 ProductGroupContext 리스트를 조립하여 반환해야 한다.")
		void shouldAssembleProductGroupContextsByCondition() {
			// Given
			ProductGroupSearchCondition searchCondition = mock(ProductGroupSearchCondition.class);
			List<ProductGroupContext> mockProductGroupContexts = List.of(mock(ProductGroupContext.class), mock(ProductGroupContext.class));
			List<ProductGroupContext> assembledContexts = List.of(mock(ProductGroupContext.class), mock(ProductGroupContext.class));

			when(productGroupFinder.fetchByCondition(searchCondition))
				.thenAnswer(invocation -> mockProductGroupContexts);
			when(productContextAssembler.assembleWithProducts(mockProductGroupContexts)).thenReturn(assembledContexts);

			// When
			List<ProductGroupContext> result = productGroupContextAssembler.assemble(searchCondition);

			// Then
			assertNotNull(result);
			assertFalse(result.isEmpty());
			assertEquals(assembledContexts, result);
			verify(productGroupFinder).fetchByCondition(searchCondition);
			verify(productContextAssembler).assembleWithProducts(mockProductGroupContexts);
		}

		@Test
		@DisplayName("조건에 맞는 데이터가 없을 경우 빈 리스트를 반환해야 한다.")
		void shouldReturnEmptyListWhenNoDataMatches() {
			// Given
			ProductGroupSearchCondition searchCondition = mock(ProductGroupSearchCondition.class);
			List<ProductGroupContext> emptyList = List.of();

			when(productGroupFinder.fetchByCondition(searchCondition))
				.thenAnswer(invocation -> emptyList);

			// When
			List<ProductGroupContext> result = productGroupContextAssembler.assemble(searchCondition);

			// Then
			assertNotNull(result);
			assertTrue(result.isEmpty());
			verify(productGroupFinder).fetchByCondition(searchCondition);
			verify(productContextAssembler, never()).assembleWithProducts(emptyList);
		}
	}

}
