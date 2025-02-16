package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupDomainBusinessValidatorTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupDomainBusinessValidator validator;

	@Mock
	private ProductGroupDomainValidator<Object> mockValidator;

	@Spy
	private ValidationResult validationResult;

	private static class TestProductGroupDomainBusinessValidator extends ProductGroupDomainBusinessValidator {

		protected TestProductGroupDomainBusinessValidator(List<ProductGroupDomainValidator<?>> mockValidator) {
			super(mockValidator);
		}

		@Override
		protected Field[] getDeclaredFields(Class<?> clazz) {
			return super.getDeclaredFields(clazz);
		}

	}


	@BeforeEach
	void setUp() {
		validator = spy(new ProductGroupDomainBusinessValidator(List.of(mockValidator)));
	}

	@Test
	@DisplayName("findMapper()가 적절한 Validator를 찾으면 Optional에 담아 반환해야 한다.")
	void shouldReturnValidatorInOptionalIfExists() {
		// Given
		Object fieldValue = new Object();
		when(mockValidator.supports(fieldValue.getClass())).thenReturn(true);

		// When
		Optional<ProductGroupDomainValidator<Object>> validatorOptional = validator.findMapper(fieldValue);

		// Then
		assertTrue(validatorOptional.isPresent());
		assertEquals(mockValidator, validatorOptional.get());
	}

	@Test
	@DisplayName("findMapper()가 적절한 Validator를 찾지 못하면 Optional.empty()를 반환해야 한다.")
	void shouldReturnEmptyOptionalIfValidatorDoesNotExist() {
		// Given
		Object fieldValue = new Object();
		when(mockValidator.supports(fieldValue.getClass())).thenReturn(false);

		// When
		Optional<ProductGroupDomainValidator<Object>> validatorOptional = validator.findMapper(fieldValue);

		// Then
		assertFalse(validatorOptional.isPresent());
	}


	@Test
	@DisplayName("필드가 없을 경우 DomainException 을 던진다.")
	void shouldThrowCoreExceptionWhenNoFieldsFound() {
		TestProductGroupDomainBusinessValidator testValidator = new TestProductGroupDomainBusinessValidator(List.of(mockValidator)) {
			@Override
			protected Field[] getDeclaredFields(Class<?> clazz) {
				return new Field[0];
			}
		};

		ProductGroupContextCommand spyCommand = spy(ProductGroupContextCommand.class);
		DomainException exception = assertThrows(DomainException.class, () -> {
			testValidator.validate(spyCommand, false);
		});

		assertTrue(exception.getMessage().contains("ProductGroupContextCommand class declaredFields cannot be empty"));
	}

	@Test
	@DisplayName("validate()에서 필드 값이 null이면 DomainException을 던져야 한다.")
	void shouldThrowDomainExceptionWhenFieldValueIsNull() throws IllegalAccessException {
		// Given
		ProductGroupContextCommand spyCommand = spy(ProductGroupContextCommand.class);

		Field[] fields = spyCommand.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (!java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
				field.set(spyCommand, null);
			}
		}

		// When & Then
		DomainException exception = assertThrows(DomainException.class, () -> validator.validate(spyCommand, false));
		assertTrue(exception.getMessage().contains("cannot be null"));
	}

	@Test
	@DisplayName("validate()에서 해당 필드의 Validator가 존재하는 경우 validate()가 호출되어야 한다.")
	void shouldCallValidateIfValidatorExists() throws IllegalAccessException {
		// Given
		ProductGroupContextCommand spyCommand = spy(ProductGroupContextCommand.class);

		Field[] fields = spyCommand.getClass().getDeclaredFields();
		Field testField = fields[0];

		testField.setAccessible(true);
		Object fieldValue = testField.get(spyCommand);

		when(mockValidator.supports(any())).thenReturn(true);
		when(validator.findMapper(fieldValue)).thenReturn(Optional.of(mockValidator));

		// When
		validator.validate(spyCommand, false);

		// Then
		verify(mockValidator).validate(eq(testField), any(ValidationResult.class), eq(false));
	}

	@Test
	@DisplayName("validate()에서 해당 필드의 Validator가 존재하지 않으면 validate()가 호출되지 않아야 한다.")
	void shouldNotCallValidateIfValidatorDoesNotExist() throws IllegalAccessException {
		// Given
		ProductGroupContextCommand spyCommand = spy(ProductGroupContextCommand.class);

		Field[] fields = spyCommand.getClass().getDeclaredFields();
		Field testField = fields[0];

		testField.setAccessible(true);
		Object fieldValue = testField.get(spyCommand);

		// Mockito 설정
		when(mockValidator.supports(any())).thenReturn(false);
		when(validator.findMapper(fieldValue)).thenReturn(Optional.empty());

		// When
		validator.validate(spyCommand, false);

		// Then
		verify(mockValidator, never()).validate(any(), any(), anyBoolean());
	}



	@Test
	@DisplayName("ValidationResult가 실패하면 throwIfInvalid()가 DomainException 을 던져야 한다.")
	void shouldThrowExceptionIfValidationFails() {
		// Given
		doThrow(new DomainException(ErrorType.BAD_REQUEST_ERROR, "Test")).when(validationResult).throwIfInvalid();

		// When & Then
		RuntimeException exception = assertThrows(RuntimeException.class, () -> validationResult.throwIfInvalid());
		assertEquals("Test", exception.getMessage());
	}



}
