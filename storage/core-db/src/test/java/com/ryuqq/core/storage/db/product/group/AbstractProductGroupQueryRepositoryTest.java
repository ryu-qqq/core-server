package com.ryuqq.core.storage.db.product.group;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.core.DefaultProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("AbstractProductGroupQueryRepository 단위 테스트")
class AbstractProductGroupQueryRepositoryTest extends BaseUnitTest {

	@Mock
	private ProductGroupContextDomainMapper productGroupContextDomainMapper;

	@Mock
	private ProductGroupQueryDslRepository productGroupQueryDslRepository;

	@InjectMocks
	private TestableProductGroupQueryRepository testRepository;


	@Nested
	@DisplayName("fetchContextById()는")
	class FetchContextById {

		@Test
		@DisplayName("존재하는 ID에 대해 DefaultProductGroupContext를 반환해야 한다.")
		void shouldReturnProductGroupContextIfExists() {
			//given
			long productGroupId = 1L;
			ProductGroupContextDto mockDto = mock(ProductGroupContextDto.class);
			DefaultProductGroupContext mockContext = mock(DefaultProductGroupContext.class);

			when(productGroupQueryDslRepository.fetchContextById(productGroupId)).thenReturn(Optional.of(mockDto));
			when(productGroupContextDomainMapper.toDomain(mockDto)).thenReturn(mockContext);

			//when
			DefaultProductGroupContext result = testRepository.fetchContextById(productGroupId);

			// Then
			assertNotNull(result);
			assertEquals(mockContext, result);

		}


		@Test
		@DisplayName("존재하지 않는 ID에 대해 DataNotFoundException을 발생시켜야 한다.")
		void shouldThrowDataNotFoundExceptionIfNotFound() {
			// Given
			long productGroupId = 999L;
			when(productGroupQueryDslRepository.fetchContextById(productGroupId)).thenReturn(Optional.empty());

			// When & Then
			DataNotFoundException exception = assertThrows(DataNotFoundException.class,
				() -> testRepository.fetchContextById(productGroupId)
			);

			assertEquals("Product Group Context not found 999", exception.getMessage());
		}

	}

	@Nested
	@DisplayName("fetchContextByCondition()는")
	class FetchContextByCondition {

		@Test
		@DisplayName("적절한 검색 조건이 주어지면 DefaultProductGroupContext 리스트를 반환해야 한다.")
		void shouldReturnProductGroupContextListIfConditionIsValid() {
			// Given
			ProductGroupSearchCondition condition = mock(ProductGroupSearchCondition.class);

			List<ProductGroupContextDto> mockDtos = List.of(mock(ProductGroupContextDto.class));
			List<DefaultProductGroupContext> expectedContexts = List.of(mock(DefaultProductGroupContext.class));

			when(productGroupQueryDslRepository.fetchContextByCondition(condition))
				.thenAnswer(invocationOnMock -> mockDtos);

			when(productGroupContextDomainMapper.toDomain(any())).thenReturn(expectedContexts.getFirst());

			// When
			List<DefaultProductGroupContext> result = testRepository.fetchContextByCondition(condition);

			// Then
			assertNotNull(result);
			assertFalse(result.isEmpty());
			assertEquals(expectedContexts, result);
		}

		@Test
		@DisplayName("검색 조건에 해당하는 결과가 없으면 빈 리스트를 반환해야 한다.")
		void shouldReturnEmptyListIfNoResultsFound() {
			// Given
			ProductGroupSearchCondition condition = mock(ProductGroupSearchCondition.class);
			when(productGroupQueryDslRepository.fetchContextByCondition(condition)).thenReturn(List.of());

			// When
			List<DefaultProductGroupContext> result = testRepository.fetchContextByCondition(condition);

			// Then
			assertNotNull(result);
			assertTrue(result.isEmpty());
		}
	}

	@Nested
	@DisplayName("countByCondition()는")
	class CountByCondition {

		@Test
		@DisplayName("적절한 검색 조건이 주어지면 개수를 반환해야 한다.")
		void shouldReturnCountByCondition() {
			// Given
			ProductGroupSearchCondition condition = mock(ProductGroupSearchCondition.class);
			long expectedCount = 10L;

			when(productGroupQueryDslRepository.countByCondition(condition)).thenReturn(expectedCount);

			// When
			long result = testRepository.countByCondition(condition);

			// Then
			assertEquals(expectedCount, result);
		}

		@Test
		@DisplayName("검색 조건에 해당하는 결과가 없으면 0을 반환해야 한다.")
		void shouldReturnZeroIfNoResultsFound() {
			// Given
			ProductGroupSearchCondition condition = mock(ProductGroupSearchCondition.class);
			when(productGroupQueryDslRepository.countByCondition(condition)).thenReturn(0L);

			// When
			long result = testRepository.countByCondition(condition);

			// Then
			assertEquals(0L, result);
		}
	}


	static class TestableProductGroupQueryRepository extends AbstractProductGroupQueryRepository {
		protected TestableProductGroupQueryRepository(ProductGroupContextDomainMapper productGroupContextDomainMapper,
													  ProductGroupQueryDslRepository productGroupQueryDslRepository) {
			super(productGroupContextDomainMapper, productGroupQueryDslRepository);
		}

		@Override
		public boolean simpleQuery() {
			return true;
		}
	}

}
