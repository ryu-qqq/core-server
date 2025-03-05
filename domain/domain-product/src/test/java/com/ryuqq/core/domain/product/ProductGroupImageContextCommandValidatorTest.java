package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;
import com.ryuqq.core.domain.product.data.TestProductGroupImageDataFactory;
import com.ryuqq.core.unit.test.BaseUnitTest;

@ExtendWith(MockitoExtension.class)
class ProductGroupImageContextCommandValidatorTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupImageContextCommandValidator validator;

	@Nested
	@DisplayName("supports() 메서드는")
	class SupportsMethod {

		@Test
		@DisplayName("ProductGroupImageContextCommand 클래스를 지원할 경우 true를 반환해야 한다.")
		void shouldReturnTrueWhenSupportsProductGroupImageContextCommand() {
			// Given
			Class<?> clazz = ProductGroupImageContextCommand.class;

			// When
			boolean result = validator.supports(clazz);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("ProductGroupImageContextCommand의 하위 클래스가 지원될 때 true를 반환해야 한다.")
		void shouldReturnTrueWhenSupportsSubclassOfProductGroupImageContextCommand() {
			// Given
			Class<?> clazz = ProductGroupImageContextCommand.class;

			// When
			boolean result = validator.supports(clazz);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("다른 클래스는 false를 반환해야 한다.")
		void shouldReturnFalseWhenSupportsOtherClass() {
			// Given
			Class<?> clazz = String.class;

			// When
			boolean result = validator.supports(clazz);

			// Then
			assertFalse(result);
		}
	}

	@Nested
	@DisplayName("validate() 메서드는")
	class ValidateMethod {

		@Test
		@DisplayName("메인 이미지가 하나도 없으면 에러를 반환해야 한다.")
		void shouldReturnErrorWhenNoMainImage() {
			// Given
			ProductGroupImageContextCommand command = TestProductGroupImageDataFactory.createContextWithoutMainImage();
			ValidationResult validationResult = mock(ValidationResult.class);

			// When
			validator.validate(command, validationResult, false);

			// Then
			verify(validationResult).addError("At least one MAIN image is required.");
		}

		@Test
		@DisplayName("메인 이미지가 두 개 이상이면 에러를 반환해야 한다.")
		void shouldReturnErrorWhenMoreThanOneMainImage() {
			// Given
			ProductGroupImageContextCommand command = TestProductGroupImageDataFactory.createContextWithMultipleMainImages();
			ValidationResult validationResult = mock(ValidationResult.class);

			// When
			validator.validate(command, validationResult, false);

			// Then
			verify(validationResult).addError("Only one MAIN image is allowed.");
		}

		@Test
		@DisplayName("메인 이미지가 정확히 하나 있을 때 에러가 발생하지 않아야 한다.")
		void shouldNotReturnErrorWhenOneMainImage() {
			// Given
			ProductGroupImageContextCommand command = TestProductGroupImageDataFactory.createContextWithMainImage();
			ValidationResult validationResult = mock(ValidationResult.class);

			// When
			validator.validate(command, validationResult, false);

			// Then
			verify(validationResult, never()).addError(anyString());
		}
	}



}
