package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.ryuqq.core.domain.product.core.Product;
import com.ryuqq.core.domain.product.core.ProductCommand;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductChecker 단위 테스트")
class ProductCheckerTest extends BaseUnitTest {

	private final ProductChecker productChecker = new ProductChecker();

	@Nested
	@DisplayName("checkUpdates() 테스트")
	class CheckUpdatesTest {

		@Test
		@DisplayName("상품 정보가 변경되지 않은 경우 false를 반환해야 한다.")
		void shouldReturnFalseWhenNoChangesDetected() {
			// Given
			Product existing = createProduct(1L, false, true, 10, BigDecimal.valueOf(1000), false);
			ProductCommand updated = createProductCommand(1L, false, true, 10, BigDecimal.valueOf(1000), false);

			// When
			boolean result = productChecker.checkUpdates(existing, updated);

			// Then
			assertFalse(result);
		}

		@Test
		@DisplayName("상품의 품절 여부가 변경된 경우 true를 반환해야 한다.")
		void shouldReturnTrueWhenSoldOutStatusChanges() {
			// Given
			Product existing = createProduct(1L, false, true, 10, BigDecimal.valueOf(1000), false);
			ProductCommand updated = createProductCommand(1L, true, true, 10, BigDecimal.valueOf(1000), false);

			// When
			boolean result = productChecker.checkUpdates(existing, updated);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("상품의 전시 상태가 변경된 경우 true를 반환해야 한다.")
		void shouldReturnTrueWhenDisplayStatusChanges() {
			// Given
			Product existing = createProduct(1L, false, true, 10, BigDecimal.valueOf(1000), false);
			ProductCommand updated = createProductCommand(1L, false, false, 10, BigDecimal.valueOf(1000), false);

			// When
			boolean result = productChecker.checkUpdates(existing, updated);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("상품의 수량이 변경된 경우 true를 반환해야 한다.")
		void shouldReturnTrueWhenQuantityChanges() {
			// Given
			Product existing = createProduct(1L, false, true, 10, BigDecimal.valueOf(1000), false);
			ProductCommand updated = createProductCommand(1L, false, true, 20, BigDecimal.valueOf(1000), false);

			// When
			boolean result = productChecker.checkUpdates(existing, updated);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("상품의 추가 가격이 변경된 경우 true를 반환해야 한다.")
		void shouldReturnTrueWhenAdditionalPriceChanges() {
			// Given
			Product existing = createProduct(1L, false, true, 10, BigDecimal.valueOf(1000), false);
			ProductCommand updated = createProductCommand(1L, false, true, 10, BigDecimal.valueOf(1500), false);

			// When
			boolean result = productChecker.checkUpdates(existing, updated);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("상품의 삭제 여부가 변경된 경우 true를 반환해야 한다.")
		void shouldReturnTrueWhenDeletedStatusChanges() {
			// Given
			Product existing = createProduct(1L, false, true, 10, BigDecimal.valueOf(1000), false);
			ProductCommand updated = createProductCommand(1L, false, true, 10, BigDecimal.valueOf(1000), true);

			// When
			boolean result = productChecker.checkUpdates(existing, updated);

			// Then
			assertTrue(result);
		}
	}

	/**
	 * ✅ 테스트를 위한 헬퍼 메서드
	 */
	private Product createProduct(Long id, boolean soldOut, boolean displayed, int quantity, BigDecimal additionalPrice, boolean deleted) {
		return new Product() {
			@Override
			public Long getId() { return id; }

			@Override
			public Long getProductGroupId() { return 1L; }

			@Override
			public boolean isSoldOut() { return soldOut; }

			@Override
			public boolean isDisplayed() { return displayed; }

			@Override
			public int getQuantity() { return quantity; }

			@Override
			public BigDecimal getAdditionalPrice() { return additionalPrice; }

			@Override
			public boolean isDeleted() { return deleted; }
		};
	}

	private ProductCommand createProductCommand(Long id, boolean soldOut, boolean displayed, int quantity, BigDecimal additionalPrice, boolean deleted) {
		return ProductCommand.of(id, 1L, soldOut, displayed, quantity, additionalPrice, deleted);
	}

}
