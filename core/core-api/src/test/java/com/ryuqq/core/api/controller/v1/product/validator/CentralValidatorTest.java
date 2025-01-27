package com.ryuqq.core.api.controller.v1.product.validator;



import org.junit.jupiter.api.DisplayName;

import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("CentralValidator 테스트")
class CentralValidatorTest extends BaseUnitTest {

	// private CentralValidator centralValidator;
	//
	// @Nested
	// @DisplayName("에러 발생 테스트")
	// class ValidationErrorTest {
	//
	// 	@BeforeEach
	// 	void setUp() {
	// 		centralValidator = new CentralValidator(List.of(
	// 			new ErrorValidator("Error 1"),
	// 			new ErrorValidator("Error 2")
	// 		));
	// 	}
	//
	// 	@TestFactory
	// 	@DisplayName("동적 에러 검증")
	// 	Stream<DynamicTest> dynamicErrorValidationTests() {
	// 		return Stream.of(
	// 			new ProductGroupContextCommandRequestDto(null, null, null, null, null, null),
	// 			new ProductGroupContextCommandRequestDto(null, null, null, null, null, List.of())
	// 		).map(requestDto -> DynamicTest.dynamicTest(
	// 			"Should add errors for requestDto: " + requestDto,
	// 			() -> {
	// 				// Act
	// 				ValidationResult result = centralValidator.validate(requestDto, false);
	//
	// 				// Assert
	// 				assertNotNull(result);
	// 				assertFalse(result.getErrors().isEmpty());
	// 				assertTrue(result.getErrors().contains("Error 1"));
	// 				assertTrue(result.getErrors().contains("Error 2"));
	// 			}
	// 		));
	// 	}
	// }
	//
	// @Nested
	// @DisplayName("Validator 호출 테스트")
	// class ValidatorCallTest {
	//
	// 	@BeforeEach
	// 	void setUp() {
	// 		Validator<ProductGroupContextCommandRequestDto> testValidator = new TestValidator();
	// 		centralValidator = new CentralValidator(List.of(testValidator));
	// 	}
	//
	// 	@TestFactory
	// 	@DisplayName("동적 호출 검증")
	// 	Stream<DynamicTest> dynamicCallValidationTests() {
	// 		return Stream.of(
	// 			new ProductGroupContextCommandRequestDto(null, null, null, null, null, null),
	// 			new ProductGroupContextCommandRequestDto(null, null, null, null, null, List.of())
	// 		).map(requestDto -> DynamicTest.dynamicTest(
	// 			"Should call validator for requestDto: " + requestDto,
	// 			() -> {
	// 				// Act
	// 				ValidationResult result = centralValidator.validate(requestDto, false);
	//
	// 				// Assert
	// 				assertNotNull(result);
	// 				assertTrue(result.getErrors().isEmpty());
	// 			}
	// 		));
	// 	}
	// }
	//
	// static class ErrorValidator implements Validator<ProductGroupContextCommandRequestDto> {
	//
	// 	private final String errorMessage;
	//
	// 	ErrorValidator(String errorMessage) {
	// 		this.errorMessage = errorMessage;
	// 	}
	//
	// 	@Override
	// 	public void validate(ProductGroupContextCommandRequestDto context, ValidationResult result, boolean updated) {
	// 		result.addError(errorMessage);
	// 	}
	// }
	//
	// static class TestValidator implements Validator<ProductGroupContextCommandRequestDto> {
	//
	// 	@Override
	// 	public void validate(ProductGroupContextCommandRequestDto context, ValidationResult result, boolean updated) {
	// 		// 호출을 확인하기 위한 더미 동작
	// 	}
	// }


}
