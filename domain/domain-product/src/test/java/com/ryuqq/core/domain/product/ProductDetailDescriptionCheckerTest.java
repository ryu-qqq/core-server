package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.ryuqq.core.domain.product.core.DefaultProductDetailDescription;
import com.ryuqq.core.domain.product.core.ProductDetailDescription;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommand;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductDetailDescriptionChecker 단위 테스트")
class ProductDetailDescriptionCheckerTest extends BaseUnitTest {
	@InjectMocks
	private ProductDetailDescriptionChecker productDetailDescriptionChecker;

	@Spy
	private UpdateDecision updateDecision;

	@Nested
	@DisplayName("checkUpdates() 테스트")
	class CheckUpdatesTest {

		@Test
		@DisplayName("기존 값과 다른 경우, assignProductGroupId()가 호출되고, UpdateDecision에 업데이트가 추가되어야 한다.")
		void shouldAssignProductGroupIdAndAddUpdateToDecisionIfChangesDetected() {
			// Given
			ProductDetailDescription existing = createProductDetailDescription("Old description");

			ProductDetailDescriptionCommand updated = spy(ProductDetailDescriptionCommand.of(
				1L, "New description"
			));

			//
			doReturn(updated).when(updated).assignProductGroupId(existing.getProductGroupId());

			// When
			productDetailDescriptionChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updated, times(1)).assignProductGroupId(existing.getProductGroupId()); // ✅ assignProductGroupId() 호출 검증

			//
			ArgumentCaptor<ProductDetailDescriptionCommand> captor = ArgumentCaptor.forClass(ProductDetailDescriptionCommand.class);
			verify(updateDecision).addUpdate(captor.capture(), eq(ProductDomainEventType.IMAGE), eq(false));

			assertSame(updated, captor.getValue()); // ✅ 업데이트된 객체가 동일한지 검증
		}

		@Test
		@DisplayName("기존 값과 동일한 경우, UpdateDecision에 업데이트를 추가하지 않아야 한다.")
		void shouldNotAddUpdateToDecisionIfNoChangesDetected() {
			// Given
			ProductDetailDescription existing = createProductDetailDescription("Same description");

			ProductDetailDescriptionCommand updated = spy(ProductDetailDescriptionCommand.of(
				1L, "Same description"
			));

			// When
			productDetailDescriptionChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updated, never()).assignProductGroupId(anyLong()); // ✅ assignProductGroupId()가 호출되지 않아야 함
			verify(updateDecision, never()).addUpdate(any(), any(), anyBoolean()); // ✅ addUpdate()도 호출되지 않아야 함
		}
	}

	@Nested
	@DisplayName("supports() 테스트")
	class SupportsTest {

		@Test
		@DisplayName("DefaultProductDetailDescription 타입이면 true를 반환해야 한다.")
		void shouldReturnTrueForDefaultProductDetailDescription() {
			// Given
			ProductDetailDescription productDetailDescription = createProductDetailDescription("Some description");

			// When
			boolean result = productDetailDescriptionChecker.supports(productDetailDescription);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("DefaultProductDetailDescription가 아닌 타입이면 false를 반환해야 한다.")
		void shouldReturnFalseForNonDefaultProductDetailDescription() {
			// Given
			Object otherType = new Object();

			// When
			boolean result = productDetailDescriptionChecker.supports(otherType);

			// Then
			assertFalse(result);
		}

		@Test
		@DisplayName("null 값을 넣으면 false를 반환해야 한다.")
		void shouldReturnFalseForNull() {
			// When
			boolean result = productDetailDescriptionChecker.supports(null);

			// Then
			assertFalse(result);
		}
	}

	private ProductDetailDescription createProductDetailDescription(String detailDescription) {
		return DefaultProductDetailDescription.create(1L, detailDescription);
	}

}
