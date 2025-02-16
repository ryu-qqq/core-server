package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Spy;

import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;
import com.ryuqq.core.domain.product.data.TestProductDataFactory;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.unit.test.BaseUnitTest;

/**
 * 옵션 이름으로 매핑되는 옵션을 찾는다.
 *
 * 1. 옵션 단 유지
 *  - 매칭되는 옵션이 없다.
 *      - 기존 옵션그룹에 새로운 옵션 디테일이 따졌다.
 *      - 기존 옵션그룹에서 아예 새로운 옵션그룹으로 변경됬다 (컬러 -> 사이즈)
 *      - 기존에 존재하는 상품들이 다 삭제 처리된다.
 *
 *  - 매칭되는 옵션이 있다.
 *  	- 기존 옵션그룹이 유지된다.
 *      - 옵션 디테일의 변경 가능성이 있다.
 * 2. 옵션 단 변경
 *
 * - 매칭 되는 옵션이 있다. (고려 대상 X)
 * 	   - 말이 안된다. (기존 블랙  -> 현재 블랙 S ) 이런식으로 글자가 추가되거나 삭제되 매칭이 될 수 가 없다.
 * - 매칭 되는 옵션이 없다.
 * 	   - 옵션 그룹의 변경 가능성이 있다. (컬러, 사이즈 -> 사이즈) or (컬러 -> 컬러,사이즈)
 *
 *
 * ---> 매칭 되는 옵션이 없을때의 공통점
 *    어떤 옵션이 추가되고 삭제됬는지
 *    이 없는 (새롭게 인풋으로 들어간) 옵션의 그룹부터 기존의 옵션과 따져봐야함
 *
 *     (삭제되는 예시) 기존 사이즈, 컬러 (-> 생각해보면 매칭 되는 옵션이 하나도 없을테니 다 삭제처리가 되긴함)
 *	   변경 후 사이즈
 *
 *	   기존의 프로덕트를 사용할수 없으니 다 삭제 후 새로 등록
 *	   이유 블랙 S가 옐로 S 중 어떤게 S로 갈건지 합쳐서갈건지 모름 이렇게 옵션 한가지만 삭제되도 어디에 매칭시켜야 할 지 알 수 없음
 *
 *	   (추가되는 예시) 기존 사이즈
 *	   변경 후 사이즈, 컬러
 *
 *	   기존의 프로덕트를 사용할수없으니 다 삭제 후 새로 등록 ( -> 생각해보면 매칭되는 옵션이 하나도 없을테니 다 삭제처리가 되긴함)
 *	   이유 (S, M, L 이던 상품이 블랙 S, 옐로 S  이렇게 컬러가 두개만 추가 되도 어디에 매칭시켜야 할 지 알 수 없음)
 *
 * 블랙 S, M
 * 블랙 L
 *
 * 블랙 S, M
 *
 * 옐로 S, M
 * 블랙 S, M
 */

@DisplayName("ProductContextCheckerTest 단위 테스트")
class ProductContextCheckerTest extends BaseUnitTest {

	@InjectMocks
	private ProductContextChecker productContextChecker;

	@Spy
	private ProductChecker productChecker;

	@Spy
	private UpdateDecision updateDecision;

	@Nested
	@DisplayName("checkUpdates() 테스트")
	class CheckUpdatesTest {

		public static Stream<Arguments> provideOptionTypesWithDifferentScenarios() {
			return Stream.of(
				Arguments.of(OptionType.SINGLE, OptionType.OPTION_ONE), // 옵션 추가 테스트
				Arguments.of(OptionType.SINGLE, OptionType.OPTION_TWO), // 옵션 추가 테스트
				Arguments.of(OptionType.OPTION_ONE, OptionType.OPTION_TWO), // 옵션 추가 테스트

				Arguments.of(OptionType.OPTION_TWO, OptionType.OPTION_ONE),  // 옵션 삭제 테스트
				Arguments.of(OptionType.OPTION_TWO, OptionType.SINGLE),  // 옵션 삭제 테스트
				Arguments.of(OptionType.OPTION_ONE, OptionType.SINGLE)  // 옵션 삭제 테스트
			);
		}

		@ParameterizedTest
		@MethodSource("provideOptionTypesWithDifferentScenarios")
		@DisplayName("새로운 옵션이 추가되었을 때, processNewProductContext()가 호출되고 업데이트가 추가되어야 한다.")
		void shouldProcessNewProductContextAndAddUpdateForNewOptions(OptionType existingOptionType, OptionType newOptionType) {
			ProductOptionContext existing = TestProductDataFactory.createProductOptionContext(1L, existingOptionType);
			ProductOptionContextCommand updated = TestProductDataFactory.createProductOptionContextCommand(1L, newOptionType);

			try (MockedStatic<ProductOptionContextProcessor> mockedStatic = mockStatic(ProductOptionContextProcessor.class)) {
				mockedStatic.when(() -> ProductOptionContextProcessor.processNewProductContext(any(), any(), any()))
					.thenReturn(updated.productCommands().getFirst());

				// When
				productContextChecker.checkUpdates(updateDecision, existing, updated);

				// Then
				mockedStatic.verify(() -> ProductOptionContextProcessor.processNewProductContext(any(), any(), any()), times(updated.productCommands().size()));
				verify(updateDecision).addUpdate(any(), eq(ProductDomainEventType.STOCK), eq(true));
			}
		}

		@ParameterizedTest
		@MethodSource("provideOptionTypesWithDifferentScenarios")
		@DisplayName("기존 옵션이 삭제되었을 때, processDeleteProductContext()가 호출되고 업데이트가 추가되어야 한다.")
		void shouldProcessDeleteProductContextAndAddUpdate(OptionType existingOptionType, OptionType removedOptionType) {
			// Given: 기존 옵션은 2단 옵션, 새로운 옵션은 1단 옵션 (삭제 발생)
			ProductOptionContext existing = TestProductDataFactory.createProductOptionContext(1L, existingOptionType);
			ProductOptionContextCommand updated = TestProductDataFactory.createProductOptionContextCommand(1L, removedOptionType);


			try(MockedStatic<ProductOptionContextProcessor> mockedStatic = mockStatic(ProductOptionContextProcessor.class)) {
				mockedStatic.when(() -> ProductOptionContextProcessor.processDeleteProductContext(any(), any()))
					.thenAnswer(invocation -> null);

				// When
				productContextChecker.checkUpdates(updateDecision, existing, updated);

				// Then
				mockedStatic.verify(() -> ProductOptionContextProcessor.processDeleteProductContext(any(), any()), times(1));
				verify(updateDecision).addUpdate(any(), eq(ProductDomainEventType.STOCK), eq(true));
			}
		}

		@ParameterizedTest
		@MethodSource("provideOptionTypesWithDifferentScenarios")
		@DisplayName("기존 값과 동일한 경우, UpdateDecision에 업데이트를 추가하지 않아야 한다.")
		void shouldNotAddUpdateIfNoChangesDetected(OptionType optionType) {
			// Given: 기존과 업데이트 값이 완전히 동일한 경우
			ProductOptionContext existing = TestProductDataFactory.createProductOptionContext(1L, optionType);
			ProductOptionContextCommand updated = TestProductDataFactory.createProductOptionContextCommand(1L, optionType);

			// When
			productContextChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(productChecker, times(updated.productCommands().size())).checkUpdates(any(), any());
			verify(updateDecision, never()).addUpdate(updated, ProductDomainEventType.STOCK, true);
		}
	}

	@Nested
	@DisplayName("supports() 테스트")
	class SupportsTest {

		@Test
		@DisplayName("DefaultProductOptionContext 타입이면 true를 반환해야 한다.")
		void shouldReturnTrueForDefaultProductOptionContext() {
			// Given
			ProductOptionContext productOptionContext = TestProductDataFactory.createProductOptionContext(1L,
				OptionType.OPTION_TWO);

			// When
			boolean result = productContextChecker.supports(productOptionContext);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("DefaultProductOptionContext가 아닌 타입이면 false를 반환해야 한다.")
		void shouldReturnFalseForNonDefaultProductOptionContext() {
			assertFalse(productContextChecker.supports(new Object()));
			assertFalse(productContextChecker.supports(null));
		}
	}


}
