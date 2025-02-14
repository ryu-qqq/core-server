package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.domain.product.core.OptionDetailCommand;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.unit.test.BaseUnitTest;

class OptionDetailHandlerTest extends BaseUnitTest {

	@InjectMocks
	private OptionDetailHandler optionDetailHandler;

	@Mock
	private OptionDetailRegister optionDetailRegister;

	@Mock
	private OptionContextCommand mockOptionContextCommandWithId;

	@Mock
	private OptionContextCommand mockOptionContextCommandWithoutId;

	private Map<OptionName, Long> optionGroupMap;

	@BeforeEach
	void setUp() {
		optionGroupMap = Map.of(OptionName.SIZE, 10L, OptionName.COLOR, 20L);
	}

	@Test
	@DisplayName("register()는 이미 존재하는 옵션 상세 ID가 있으면 그대로 유지해야 한다.")
	void shouldKeepExistingOptionDetailId() {
		// Given
		when(mockOptionContextCommandWithId.optionValue()).thenReturn("Large");
		when(mockOptionContextCommandWithId.optionDetailId()).thenReturn(100L);

		List<OptionContextCommand> optionCommands = List.of(mockOptionContextCommandWithId);

		// When
		Map<String, Long> result = optionDetailHandler.register(optionCommands, optionGroupMap);

		// Then
		assertEquals(1, result.size());
		assertEquals(100L, result.get("Large"));
		verify(optionDetailRegister, never()).register(any());
	}

	@Test
	@DisplayName("register()는 옵션 상세 ID가 없으면 새로운 옵션 상세를 등록해야 한다.")
	void shouldRegisterNewOptionDetailIfIdNotExists() {
		// Given
		when(mockOptionContextCommandWithoutId.optionValue()).thenReturn("Red");
		when(mockOptionContextCommandWithoutId.optionDetailId()).thenReturn(0L);
		when(mockOptionContextCommandWithoutId.optionName()).thenReturn(OptionName.COLOR);
		when(optionDetailRegister.register(any(OptionDetailCommand.class))).thenReturn(200L);

		List<OptionContextCommand> optionCommands = List.of(mockOptionContextCommandWithoutId);

		// When
		Map<String, Long> result = optionDetailHandler.register(optionCommands, optionGroupMap);

		// Then
		assertEquals(1, result.size());
		assertEquals(200L, result.get("Red"));
		verify(optionDetailRegister).register(any(OptionDetailCommand.class));
	}

	@Test
	@DisplayName("register()에서 빈 옵션 리스트가 주어지면 빈 Map을 반환해야 한다.")
	void shouldReturnEmptyMapWhenEmptyOptionList() {
		// Given
		List<OptionContextCommand> emptyList = List.of();

		// When
		Map<String, Long> result = optionDetailHandler.register(emptyList, optionGroupMap);

		// Then
		assertTrue(result.isEmpty());
		verify(optionDetailRegister, never()).register(any());
	}
}
