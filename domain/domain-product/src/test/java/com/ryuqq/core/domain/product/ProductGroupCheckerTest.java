package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.ryuqq.core.domain.product.core.DefaultProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupCommand;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.ProductStatus;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductGroupChecker 단위 테스트")
class ProductGroupCheckerTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupChecker productGroupChecker;

	@Spy
	private UpdateDecision updateDecision;

	@Nested
	@DisplayName("checkUpdates() 테스트")
	class CheckUpdatesTest {

		@Test
		@DisplayName("기존 값과 다른 경우, UpdateDecision에 업데이트가 추가되어야 한다.")
		void shouldAddUpdateToDecisionWhenProductGroupChanges() {
			// Given
			ProductGroup existing = createProductGroup(
				"Old Name", "OLD_STYLE", ProductCondition.NEW, ManagementType.AUTO, OptionType.OPTION_ONE,
				BigDecimal.valueOf(1000), BigDecimal.valueOf(800), false, true, ProductStatus.WAITING, "Old Keyword"
			);

			ProductGroupCommand updated = createProductGroupCommand(
				"New Name", "NEW_STYLE", ProductCondition.USED, ManagementType.SABANG, OptionType.OPTION_TWO,
				BigDecimal.valueOf(1200), BigDecimal.valueOf(1000), true, false, "New Keyword"
			);

			// When
			productGroupChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			ArgumentCaptor<ProductGroupCommand> captor = ArgumentCaptor.forClass(ProductGroupCommand.class);
			verify(updateDecision).addUpdate(captor.capture(), eq(ProductDomainEventType.PRODUCT_GROUP), eq(false));

			assertSame(updated, captor.getValue());
		}

		@Test
		@DisplayName("가격 정보가 변경되었을 경우 가격 업데이트를 추가해야 한다.")
		void shouldAddPriceUpdateToDecisionWhenPriceChanges() {
			// Given
			ProductGroup existing = createProductGroup(
				"Old Name", "OLD_STYLE", ProductCondition.NEW, ManagementType.AUTO, OptionType.OPTION_ONE,
				BigDecimal.valueOf(1000), BigDecimal.valueOf(800), false, true, ProductStatus.WAITING, "Old Keyword"
			);

			ProductGroupCommand updated = createProductGroupCommand(
				"Old Name", "OLD_STYLE", ProductCondition.NEW, ManagementType.AUTO, OptionType.OPTION_ONE,
				BigDecimal.valueOf(1200), BigDecimal.valueOf(1000), false, true, "Old Keyword"
			);

			// When
			productGroupChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			ArgumentCaptor<ProductGroupCommand> captor = ArgumentCaptor.forClass(ProductGroupCommand.class);
			verify(updateDecision).addUpdate(captor.capture(), eq(ProductDomainEventType.PRICE), eq(true));

			assertSame(updated, captor.getValue()); // ✅ 가격이 변경된 객체가 올바르게 전달되었는지 확인
		}

		@Test
		@DisplayName("변경 사항이 없으면 UpdateDecision에 업데이트를 추가하지 않아야 한다.")
		void shouldNotAddUpdateToDecisionWhenNoChanges() {
			// Given
			ProductGroup existing = createProductGroup(
				"Old Name", "OLD_STYLE", ProductCondition.NEW, ManagementType.AUTO, OptionType.OPTION_ONE,
				BigDecimal.valueOf(1000), BigDecimal.valueOf(800), false, true, ProductStatus.WAITING, "Old Keyword"
			);

			ProductGroupCommand updated = createProductGroupCommand(
				"Old Name", "OLD_STYLE", ProductCondition.NEW, ManagementType.AUTO, OptionType.OPTION_ONE,
				BigDecimal.valueOf(1000), BigDecimal.valueOf(800), false, true, "Old Keyword"
			);

			// When
			productGroupChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updateDecision, never()).addUpdate(any(), any(), anyBoolean()); // ✅ 업데이트가 추가되지 않았는지 확인
		}
	}

	@Nested
	@DisplayName("supports() 테스트")
	class SupportsTest {

		@Test
		@DisplayName("DefaultProductGroup 인스턴스를 지원해야 한다.")
		void shouldSupportDefaultProductGroup() {
			assertTrue(productGroupChecker.supports(createProductGroup(
				"Old Name", "OLD_STYLE", ProductCondition.NEW, ManagementType.AUTO, OptionType.OPTION_ONE,
				BigDecimal.valueOf(1000), BigDecimal.valueOf(800), false, true, ProductStatus.WAITING, "Old Keyword"
			)));
		}

		@Test
		@DisplayName("DefaultProductGroup 이외의 객체를 지원하지 않아야 한다.")
		void shouldNotSupportOtherObjects() {
			assertFalse(productGroupChecker.supports(new Object()));
			assertFalse(productGroupChecker.supports(null));
		}
	}


	private ProductGroup createProductGroup(String name, String styleCode, ProductCondition condition, ManagementType managementType, OptionType optionType,
											BigDecimal price, BigDecimal cost, boolean isRefundable, boolean isExchangeable, ProductStatus status, String keyword) {
		return DefaultProductGroup.create(1L, 2L, 3L, 4L, name, styleCode, condition, managementType, optionType, price, cost, isRefundable, isExchangeable, status, keyword);
	}


	private ProductGroupCommand createProductGroupCommand(String name, String styleCode, ProductCondition condition, ManagementType managementType, OptionType optionType,
														  BigDecimal price, BigDecimal cost, boolean isRefundable, boolean isExchangeable, String keyword) {
		return ProductGroupCommand.of(1L, 2L, 3L, 4L, name, styleCode, condition, managementType, optionType, price, cost, isRefundable, isExchangeable, keyword);
	}

}
