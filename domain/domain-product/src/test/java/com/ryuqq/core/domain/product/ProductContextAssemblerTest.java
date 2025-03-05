package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductContextAssembler 테스트")
class ProductContextAssemblerTest extends BaseUnitTest {

	@Mock
	private ProductFinder productFinder;

	@InjectMocks
	private ProductContextAssembler productContextAssembler;

	@Nested
	@DisplayName("assembleWithProducts(ProductGroupContext)는")
	class AssembleWithSingleProductGroupContextTests {

		@Test
		@DisplayName("ProductOptionContext를 가져와 조립한 ProductGroupContext를 반환해야 한다.")
		void shouldAssembleSingleProductGroupContext() {
			// Given
			ProductGroupContext mockProductGroupContext = mock(ProductGroupContext.class);
			ProductOptionContext mockProductOptionContext = mock(ProductOptionContext.class);

			long productGroupId = 1L;
			when(mockProductGroupContext.getId()).thenReturn(productGroupId);
			when(productFinder.fetchByProductGroupId(productGroupId)).thenReturn(mockProductOptionContext);

			// When
			ProductGroupContext result = productContextAssembler.assembleWithProducts(mockProductGroupContext);

			// Then
			assertNotNull(result);
			verify(productFinder).fetchByProductGroupId(productGroupId);
		}
	}

	@Nested
	@DisplayName("assembleWithProducts(List<ProductGroupContext>)는")
	class AssembleWithMultipleProductGroupContextTests {

		@Test
		@DisplayName("각 ProductGroupContext에 ProductOptionContext를 조합하여 반환해야 한다.")
		void shouldAssembleMultipleProductGroupContexts() {
			// Given
			ProductGroupContext mockProductGroup1 = mock(ProductGroupContext.class);
			ProductGroupContext mockProductGroup2 = mock(ProductGroupContext.class);
			ProductOptionContext mockProductOption1 = mock(ProductOptionContext.class);
			ProductOptionContext mockProductOption2 = mock(ProductOptionContext.class);

			long productGroupId1 = 1L;
			long productGroupId2 = 2L;
			when(mockProductGroup1.getId()).thenReturn(productGroupId1);
			when(mockProductGroup2.getId()).thenReturn(productGroupId2);

			List<ProductGroupContext> inputList = List.of(mockProductGroup1, mockProductGroup2);
			when(productFinder.fetchByProductGroupIds(List.of(productGroupId1, productGroupId2)))
				.thenAnswer(invocation -> List.of(mockProductOption1, mockProductOption2));

			// When
			List<ProductGroupContext> result = productContextAssembler.assembleWithProducts(inputList);

			// Then
			assertNotNull(result);
			assertEquals(2, result.size());
			verify(productFinder).fetchByProductGroupIds(List.of(productGroupId1, productGroupId2));
		}

		@Test
		@DisplayName("빈 리스트가 주어지면 빈 리스트를 반환해야 한다.")
		void shouldReturnEmptyListWhenInputIsEmpty() {
			// Given
			List<ProductGroupContext> emptyList = List.of();

			// When
			List<ProductGroupContext> result = productContextAssembler.assembleWithProducts(emptyList);

			// Then
			assertNotNull(result);
			assertTrue(result.isEmpty());

			verify(productFinder, never()).fetchByProductGroupIds(any());
		}

	}
}
