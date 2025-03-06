package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.domain.product.dao.group.ProductGroupQueryRepository;
import com.ryuqq.core.domain.product.dao.group.ProductGroupQueryRepositoryProvider;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductGroupFinder 단위 테스트")
class ProductGroupFinderTest extends BaseUnitTest {
	@Mock
	private ProductGroupQueryRepositoryProvider productGroupQueryRepositoryProvider;

	@Mock
	private ProductGroupQueryRepository productGroupQueryRepository;

	@InjectMocks
	private ProductGroupFinder productGroupFinder;

	@Nested
	@DisplayName("fetchProductContextById()는")
	class FetchProductContextById {

		@Test
		@DisplayName("존재하는 productGroupId로 호출하면 ProductGroupContext를 반환해야 한다.")
		void shouldReturnProductGroupContextIfExists() {
			// Given
			long productGroupId = 1L;
			ProductGroupContext mockContext = mock(ProductGroupContext.class);
			when(productGroupQueryRepositoryProvider.getProductGroupQueryRepository(true)).thenReturn(productGroupQueryRepository);
			when(productGroupQueryRepository.fetchContextById(productGroupId)).thenReturn(mockContext);

			// When
			ProductGroupContext result = productGroupFinder.fetchProductContextById(productGroupId);

			// Then
			assertNotNull(result);
			assertEquals(mockContext, result);
		}
	}

	@Nested
	@DisplayName("fetchByCondition()는")
	class FetchByCondition {

		@Test
		@DisplayName("적절한 검색 조건이 주어지면 ProductGroupContext 리스트를 반환해야 한다.")
		void shouldReturnProductGroupContextListIfConditionIsValid() {
			// Given
			ProductGroupSearchCondition condition = mock(ProductGroupSearchCondition.class);
			List<ProductGroupContext> mockList = List.of(mock(ProductGroupContext.class));
			when(condition.isSimpleQuery()).thenReturn(true);
			when(productGroupQueryRepositoryProvider.getProductGroupQueryRepository(true)).thenReturn(productGroupQueryRepository);
			when(productGroupQueryRepository.fetchContextByCondition(condition))
				.thenAnswer(invocationOnMock -> mockList);

			// When
			List<? extends ProductGroupContext> result = productGroupFinder.fetchByCondition(condition);

			// Then
			assertNotNull(result);
			assertFalse(result.isEmpty());
			assertEquals(mockList, result);
		}
	}

	@Nested
	@DisplayName("countByCondition()는")
	class CountByCondition {

		@Test
		@DisplayName("적절한 검색 조건이 주어지면 결과 개수를 반환해야 한다.")
		void shouldReturnCountByCondition() {
			// Given
			ProductGroupSearchCondition condition = mock(ProductGroupSearchCondition.class);
			long expectedCount = 5L;
			when(condition.isSimpleQuery()).thenReturn(false);
			when(productGroupQueryRepositoryProvider.getProductGroupQueryRepository(false)).thenReturn(productGroupQueryRepository);
			when(productGroupQueryRepository.countByCondition(condition)).thenReturn(expectedCount);

			// When
			long result = productGroupFinder.countByCondition(condition);

			// Then
			assertEquals(expectedCount, result);
		}
	}

}
