package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.domain.product.core.OptionGroupCommand;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.unit.test.BaseUnitTest;

class OptionGroupHandlerTest extends BaseUnitTest {


	@InjectMocks
	private OptionGroupHandler optionGroupHandler;

	@Mock
	private OptionGroupRegister optionGroupRegister;

	@Mock
	private OptionContextCommand mockOptionContextCommandWithId;

	@Mock
	private OptionContextCommand mockOptionContextCommandWithoutId;

	@Test
	@DisplayName("register()는 이미 존재하는 옵션 그룹 ID가 있으면 그대로 유지해야 한다.")
	void shouldKeepExistingOptionGroupId() {
		// Given
		when(mockOptionContextCommandWithId.optionName()).thenReturn(OptionName.SIZE);
		when(mockOptionContextCommandWithId.optionGroupId()).thenReturn(10L);

		List<OptionContextCommand> optionCommands = List.of(mockOptionContextCommandWithId);

		// When
		Map<OptionName, Long> result = optionGroupHandler.register(optionCommands);

		// Then
		assertEquals(1, result.size());
		assertEquals(10L, result.get(OptionName.SIZE));
		verify(optionGroupRegister, never()).register(any());
	}

	@Test
	@DisplayName("register()는 옵션 그룹 ID가 없으면 새로운 옵션 그룹을 등록해야 한다.")
	void shouldRegisterNewOptionGroupIfIdNotExists() {
		// Given
		when(mockOptionContextCommandWithoutId.optionName()).thenReturn(OptionName.COLOR);
		when(mockOptionContextCommandWithoutId.optionGroupId()).thenReturn(0L);
		when(optionGroupRegister.register(any(OptionGroupCommand.class))).thenReturn(20L);

		List<OptionContextCommand> optionCommands = List.of(mockOptionContextCommandWithoutId);

		// When
		Map<OptionName, Long> result = optionGroupHandler.register(optionCommands);

		// Then
		assertEquals(1, result.size());
		assertEquals(20L, result.get(OptionName.COLOR));
		verify(optionGroupRegister).register(any(OptionGroupCommand.class));
	}

	@Test
	@DisplayName("register()에서 빈 옵션 리스트가 주어지면 빈 Map을 반환해야 한다.")
	void shouldReturnEmptyMapWhenEmptyOptionList() {
		// Given
		List<OptionContextCommand> emptyList = List.of();

		// When
		Map<OptionName, Long> result = optionGroupHandler.register(emptyList);

		// Then
		assertTrue(result.isEmpty());
		verify(optionGroupRegister, never()).register(any());
	}

}
