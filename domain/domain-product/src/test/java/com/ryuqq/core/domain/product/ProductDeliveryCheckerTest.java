package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.ryuqq.core.domain.Money;
import com.ryuqq.core.domain.product.core.DefaultProductDelivery;
import com.ryuqq.core.domain.product.core.ProductDelivery;
import com.ryuqq.core.domain.product.core.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductDeliveryChecker 단위 테스트")
class ProductDeliveryCheckerTest extends BaseUnitTest {

	@InjectMocks
	private ProductDeliveryChecker productDeliveryChecker;

	@Spy
	private UpdateDecision updateDecision;

	@Nested
	@DisplayName("checkUpdates() 테스트")
	class CheckUpdatesTest {

		@Test
		@DisplayName("기존 값과 다른 경우, assignProductGroupId()가 호출되고, UpdateDecision에 업데이트가 추가되어야 한다.")
		void shouldAssignProductGroupIdAndAddUpdateToDecisionIfChangesDetected() {
			// Given
			ProductDelivery existing = createProductDelivery("Seoul", BigDecimal.valueOf(2500), 3, ReturnMethod.RETURN_SELLER, ShipmentCompanyCode.SHIP01, BigDecimal.valueOf(5000), "Korea");

			ProductDeliveryCommand updated = spy(ProductDeliveryCommand.of(
				1L, "Busan", BigDecimal.valueOf(3000), 5, ReturnMethod.RETURN_CONSUMER, ShipmentCompanyCode.SHIP04, BigDecimal.valueOf(6000), "International"
			));

			//
			doReturn(updated).when(updated).assignProductGroupId(existing.getProductGroupId());

			// When
			productDeliveryChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updated, times(1)).assignProductGroupId(existing.getProductGroupId());

			//
			ArgumentCaptor<ProductDeliveryCommand> captor = ArgumentCaptor.forClass(ProductDeliveryCommand.class);
			verify(updateDecision).addUpdate(captor.capture(), eq(ProductDomainEventType.DELIVERY), eq(false));

			assertSame(updated, captor.getValue());
		}

		@Test
		@DisplayName("기존 값과 동일한 경우, UpdateDecision에 업데이트를 추가하지 않아야 한다.")
		void shouldNotAddUpdateToDecisionIfNoChangesDetected() {
			// Given
			ProductDelivery existing = createProductDelivery("Seoul", BigDecimal.valueOf(2500), 3, ReturnMethod.RETURN_CONSUMER, ShipmentCompanyCode.SHIP04, BigDecimal.valueOf(5000), "Korea");

			ProductDeliveryCommand updated = spy(ProductDeliveryCommand.of(
				1L, "Seoul", BigDecimal.valueOf(2500), 3, ReturnMethod.RETURN_CONSUMER, ShipmentCompanyCode.SHIP04, BigDecimal.valueOf(5000), "Korea"
			));

			// When
			productDeliveryChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updated, never()).assignProductGroupId(anyLong()); // ✅ assignProductGroupId()가 호출되지 않아야 함
			verify(updateDecision, never()).addUpdate(any(), any(), anyBoolean()); // ✅ addUpdate()도 호출되지 않아야 함
		}
	}

	@Nested
	@DisplayName("supports() 테스트")
	class SupportsTest {

		@Test
		@DisplayName("DefaultProductDelivery 타입이면 true를 반환해야 한다.")
		void shouldReturnTrueForDefaultProductDelivery() {
			// Given
			ProductDelivery productDelivery = createProductDelivery("Seoul", BigDecimal.valueOf(2500), 3, ReturnMethod.RETURN_CONSUMER, ShipmentCompanyCode.SHIP04, BigDecimal.valueOf(5000), "Korea");

			// When
			boolean result = productDeliveryChecker.supports(productDelivery);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("DefaultProductDelivery가 아닌 타입이면 false를 반환해야 한다.")
		void shouldReturnFalseForNonDefaultProductDelivery() {
			// Given
			Object otherType = new Object();

			// When
			boolean result = productDeliveryChecker.supports(otherType);

			// Then
			assertFalse(result);
		}

		@Test
		@DisplayName("null 값을 넣으면 false를 반환해야 한다.")
		void shouldReturnFalseForNull() {
			// When
			boolean result = productDeliveryChecker.supports(null);

			// Then
			assertFalse(result);
		}
	}

	// ✅ 가독성을 높이기 위해 ProductDelivery 생성 헬퍼 메서드 추가
	private ProductDelivery createProductDelivery(String deliveryArea, BigDecimal deliveryFee, int deliveryPeriodAverage, ReturnMethod returnMethodDomestic, ShipmentCompanyCode returnCourierDomestic,
												  BigDecimal returnChargeDomestic, String returnExchangeAreaDomestic) {
		return DefaultProductDelivery.create(1L, deliveryArea, Money.wons(deliveryFee), deliveryPeriodAverage, returnMethodDomestic, returnCourierDomestic, Money.wons(returnChargeDomestic), returnExchangeAreaDomestic);
	}
}
