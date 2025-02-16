package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.ryuqq.core.domain.product.core.DefaultProductNotice;
import com.ryuqq.core.domain.product.core.ProductNotice;
import com.ryuqq.core.domain.product.dao.notice.ProductNoticeCommand;
import com.ryuqq.core.enums.Origin;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductNoticeChecker 단위 테스트")
class ProductNoticeCheckerTest extends BaseUnitTest {

	@InjectMocks
	private ProductNoticeChecker productNoticeChecker;

	@Spy
	private UpdateDecision updateDecision;

	@Nested
	@DisplayName("checkUpdates() 테스트")
	class CheckUpdatesTest {

		@Test
		@DisplayName("기존 값과 다른 경우, assignProductGroupId()가 호출되고, UpdateDecision에 업데이트가 추가되어야 한다.")
		void shouldAssignProductGroupIdAndAddUpdateToDecisionIfChangesDetected() {
			// Given
			ProductNotice existing = createProductNotice(1L, "Cotton", "Red", "L", "Nike", Origin.KR, "Hand Wash", "2024-01", "ISO9001", "010-1234-5678");

			ProductNoticeCommand updated = spy(ProductNoticeCommand.of(
				2L, "Silk", "Blue", "M", "Adidas", Origin.CN, "Machine Wash", "2025-01", "ISO14001", "010-9876-5432"
			));

			doReturn(updated).when(updated).assignProductGroupId(existing.getProductGroupId());

			// When
			productNoticeChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updated, times(1)).assignProductGroupId(existing.getProductGroupId());

			ArgumentCaptor<ProductNoticeCommand> captor = ArgumentCaptor.forClass(ProductNoticeCommand.class);
			verify(updateDecision).addUpdate(captor.capture(), eq(ProductDomainEventType.NOTICE), eq(false));

			//
			assertSame(updated, captor.getValue());
		}

		@Test
		@DisplayName("기존 값과 동일한 경우, UpdateDecision에 업데이트를 추가하지 않아야 한다.")
		void shouldNotAddUpdateToDecisionIfNoChangesDetected() {
			// Given
			ProductNotice existing = createProductNotice(1L, "Cotton", "Red", "L", "Nike", Origin.KR, "Hand Wash", "2024-01", "ISO9001", "010-1234-5678");

			ProductNoticeCommand updated = spy(ProductNoticeCommand.of(
				1L, "Cotton", "Red", "L", "Nike", Origin.KR, "Hand Wash", "2024-01", "ISO9001", "010-1234-5678"
			));

			// When
			productNoticeChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updated, never()).assignProductGroupId(anyLong());
			verify(updateDecision, never()).addUpdate(any(), any(), anyBoolean());
		}
	}

	@Nested
	@DisplayName("supports() 테스트")
	class SupportsTest {

		@Test
		@DisplayName("DefaultProductNotice 타입이면 true를 반환해야 한다.")
		void shouldReturnTrueForDefaultProductNotice() {
			// Given
			ProductNotice productNotice = createProductNotice(1L, "Cotton", "Red", "L", "Nike", Origin.KR, "Hand Wash", "2024-01", "ISO9001", "010-1234-5678");

			// When
			boolean result = productNoticeChecker.supports(productNotice);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("DefaultProductNotice가 아닌 타입이면 false를 반환해야 한다.")
		void shouldReturnFalseForNonDefaultProductNotice() {
			// Given
			Object otherType = new Object();

			// When
			boolean result = productNoticeChecker.supports(otherType);

			// Then
			assertFalse(result);
		}

		@Test
		@DisplayName("null 값을 넣으면 false를 반환해야 한다.")
		void shouldReturnFalseForNull() {
			// When
			boolean result = productNoticeChecker.supports(null);

			// Then
			assertFalse(result);
		}
	}

	private ProductNotice createProductNotice(Long productGroupId, String material, String color, String size, String maker, Origin origin,
											  String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
		return DefaultProductNotice.create(productGroupId, material, color, size, maker, origin, washingMethod, yearMonth, assuranceStandard, asPhone);
	}


}
