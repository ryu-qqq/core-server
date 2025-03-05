package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.DefaultProductOptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;
import com.ryuqq.core.domain.product.data.TestProductDataFactory;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductOptionValidatorTest extends BaseUnitTest {

	@Mock
	private ValidationResult validationResult;

	@InjectMocks
	private  ProductOptionValidator validator;

	@Test
	@DisplayName("옵션이 비어 있으면 에러를 반환해야 한다.")
	void shouldReturnErrorWhenProductOptionsAreEmpty() {
		// Given
		ProductOptionContextCommand command = mock(ProductOptionContextCommand.class);
		when(command.productCommands()).thenReturn(List.of());

		// When
		when(command.optionType()).thenReturn(OptionType.OPTION_TWO);
		validator.validate(command, validationResult, false);

		// Then
		verify(validationResult).addError("product options can not be empty.");
	}

	@Test
	@DisplayName("Single 옵션 타입에 여러 옵션이 포함되면 에러를 반환해야 한다.")
	void shouldReturnErrorWhenMultipleOptionsForSingleOptionType() {
		// Given
		OptionContextCommand testColor = TestProductDataFactory.createOptionContextCommand(OptionName.COLOR,
			"TEST COLOR");

		ProductOptionCommand productOptionCommand = TestProductDataFactory.createProductOptionCommand(null, false,
			List.of(testColor));

		ProductOptionContextCommand productOptionContextCommand = new DefaultProductOptionContextCommand(0L, OptionType.SINGLE, List.of(productOptionCommand));

		// When
		validator.validate(productOptionContextCommand, validationResult, false);

		// Then
		verify(validationResult).addError("Single option type does not allow options.");
	}

	@Test
	@DisplayName("옵션 타입이 OPTION_ONE일 때, 옵션 이름이 하나만 있어야 하며 그 외에는 에러를 반환해야 한다.")
	void shouldReturnErrorWhenOptionOneHasMultipleOptions() {
		// Given
		OptionContextCommand testColor = TestProductDataFactory.createOptionContextCommand(OptionName.COLOR,
			"TEST COLOR");

		OptionContextCommand testSize = TestProductDataFactory.createOptionContextCommand(OptionName.SIZE,
			"TEST SIZE");

		ProductOptionCommand productOptionCommand = TestProductDataFactory.createProductOptionCommand(null, false,
			List.of(testColor, testSize));

		ProductOptionContextCommand productOptionContextCommand = new DefaultProductOptionContextCommand(0L, OptionType.OPTION_ONE, List.of(productOptionCommand));


		// When
		validator.validate(productOptionContextCommand, validationResult, false);

		// Then
		verify(validationResult).addError("One step option type should have only one unique OptionName.");
	}


	@Test
	@DisplayName("옵션 타입이 OPTION_TWO일 때, 두 개의 옵션 이름이 COLOR와 SIZE이어야 한다.")
	void shouldReturnErrorWhenOptionTwoHasInvalidOptionCombination() {
		// Given
		OptionContextCommand testColor = TestProductDataFactory.createOptionContextCommand(OptionName.COLOR,
			"TEST COLOR");

		OptionContextCommand testSize = TestProductDataFactory.createOptionContextCommand(OptionName.DEFAULT_ONE,
			"TEST SIZE");

		ProductOptionCommand productOptionCommand = TestProductDataFactory.createProductOptionCommand(null, false,
			List.of(testColor, testSize));

		ProductOptionContextCommand productOptionContextCommand = new DefaultProductOptionContextCommand(0L, OptionType.OPTION_TWO, List.of(productOptionCommand));

		// When
		validator.validate(productOptionContextCommand, validationResult, false);

		// Then
		verify(validationResult).addError("Two step options must be a valid combination of Color and Size, or Default_One and Default_Two.");
	}


	@Test
	@DisplayName("제품 등록 시 productId가 포함되면 안 된다.")
	void shouldReturnErrorWhenProductIdIsIncludedDuringFirstEnrollment() {
		// Given
		ProductOptionContextCommand command = TestProductDataFactory.createProductOptionContextCommand(0L, OptionType.SINGLE);

		// When
		validator.validate(command, validationResult, false);

		// Then
		verify(validationResult).addError("When first enrolling a product, productId must not be included.");
	}

}
