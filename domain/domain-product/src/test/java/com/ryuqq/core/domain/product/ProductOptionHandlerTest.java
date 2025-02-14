package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductOptionHandlerTest extends BaseUnitTest {


	@InjectMocks
	private ProductOptionHandler productOptionHandler;

	@Mock
	private ProductOptionRegister productOptionRegister;

	@Mock
	private OptionContextCommand mockOptionContextCommand;

	private Map<OptionName, Long> optionGroupMap;
	private Map<String, Long> optionDetailMap;

	@BeforeEach
	void setUp() {
		optionGroupMap = Map.of(OptionName.SIZE, 10L, OptionName.COLOR, 20L);
		optionDetailMap = Map.of("Large", 100L, "Red", 200L);
	}

	@Test
	@DisplayName("register()는 ProductOptionRegister에 옵션을 등록해야 한다.")
	void shouldRegisterProductOptions() {
		// Given
		Long productId = 1L;
		when(mockOptionContextCommand.optionName()).thenReturn(OptionName.SIZE);
		when(mockOptionContextCommand.optionValue()).thenReturn("Large");

		List<OptionContextCommand> optionCommands = List.of(mockOptionContextCommand);

		// When
		productOptionHandler.register(productId, optionCommands, optionGroupMap, optionDetailMap);

		// Then
		verify(productOptionRegister).register(anyList());
	}

	@Test
	@DisplayName("register()에서 옵션 그룹이 존재하지 않으면 예외를 던져야 한다.")
	void shouldThrowExceptionIfOptionGroupNotFound() {
		// Given
		Long productId = 1L;
		when(mockOptionContextCommand.optionName()).thenReturn(OptionName.SIZE);
		when(mockOptionContextCommand.optionValue()).thenReturn("Large");

		List<OptionContextCommand> optionCommands = List.of(mockOptionContextCommand);

		// 옵션 그룹 맵이 비어있음
		Map<OptionName, Long> emptyOptionGroupMap = Map.of();

		// When & Then
		DomainException exception = assertThrows(DomainException.class, () ->
			productOptionHandler.register(productId, optionCommands, emptyOptionGroupMap, optionDetailMap)
		);

		assertTrue(exception.getMessage().contains("Option group not found"));
	}

	@Test
	@DisplayName("register()에서 옵션 상세 정보가 존재하지 않으면 예외를 던져야 한다.")
	void shouldThrowExceptionIfOptionDetailNotFound() {
		// Given
		Long productId = 1L;
		when(mockOptionContextCommand.optionName()).thenReturn(OptionName.SIZE);
		when(mockOptionContextCommand.optionValue()).thenReturn("Large");

		List<OptionContextCommand> optionCommands = List.of(mockOptionContextCommand);

		// 옵션 상세 정보 맵이 비어있음
		Map<String, Long> emptyOptionDetailMap = Map.of();

		// When & Then
		DomainException exception = assertThrows(DomainException.class, () ->
			productOptionHandler.register(productId, optionCommands, optionGroupMap, emptyOptionDetailMap)
		);

		assertTrue(exception.getMessage().contains("Option detail not found"));
	}

}
