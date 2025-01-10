package com.ryuqq.core.storage.db;

import com.ryuqq.core.unit.test.BaseUnitTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BooleanToIntegerConverterTest extends BaseUnitTest {

	private final BooleanToIntegerConverter converter = new BooleanToIntegerConverter();

	@Test
	@DisplayName("convertToDatabaseColumn: Boolean 값을 Integer 값으로 변환해야 한다")
	void shouldConvertBooleanToInteger() {
		// Given, When, Then
		assertAll(
			() -> assertEquals(1, converter.convertToDatabaseColumn(true), "true는 1로 변환되어야 함"),
			() -> assertEquals(0, converter.convertToDatabaseColumn(false), "false는 0으로 변환되어야 함"),
			() -> assertEquals(0, converter.convertToDatabaseColumn(null), "null은 0으로 변환되어야 함")
		);
	}

	@Test
	@DisplayName("convertToEntityAttribute: Integer 값을 Boolean 값으로 변환해야 한다")
	void shouldConvertIntegerToBoolean() {
		// Given, When, Then
		assertAll(
			() -> assertTrue(converter.convertToEntityAttribute(1), "1은 true로 변환되어야 함"),
			() -> assertFalse(converter.convertToEntityAttribute(0), "0은 false로 변환되어야 함"),
			() -> assertFalse(converter.convertToEntityAttribute(null), "null은 false로 변환되어야 함")
		);
	}


}
