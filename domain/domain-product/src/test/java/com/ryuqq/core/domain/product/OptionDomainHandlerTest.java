package com.ryuqq.core.domain.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.unit.test.BaseUnitTest;

class OptionDomainHandlerTest extends BaseUnitTest {

	@InjectMocks
	private OptionDomainHandler optionDomainHandler;

	@Mock
	private OptionGroupHandler optionGroupHandler;

	@Mock
	private OptionDetailHandler optionDetailHandler;

	@Mock
	private ProductOptionHandler productOptionHandler;

	@Mock
	private OptionContextCommand mockOptionContextCommand;


	@Test
	@DisplayName("handle()은 유효한 옵션 데이터를 처리해야 한다.")
	void shouldHandleValidOptionData() {
		// Given
		long productId = 100L;
		List<OptionContextCommand> optionCommands = List.of(mockOptionContextCommand);
		Map<Long, List<? extends OptionContextCommand>> productOptionCommandMap = Map.of(productId, optionCommands);

		Map<OptionName, Long> optionGroupMap = Map.of(OptionName.SIZE, 1L);
		Map<String, Long> optionDetailMap = Map.of("M", 2L);

		when(optionGroupHandler.register(optionCommands)).thenReturn(optionGroupMap);
		when(optionDetailHandler.register(optionCommands, optionGroupMap)).thenReturn(optionDetailMap);

		// When
		optionDomainHandler.handle(productOptionCommandMap);

		// Then
		verify(optionGroupHandler).register(optionCommands);
		verify(optionDetailHandler).register(optionCommands, optionGroupMap);
		verify(productOptionHandler).register(productId, optionCommands, optionGroupMap, optionDetailMap);
	}

	@Test
	@DisplayName("handle()에서 빈 옵션 맵이 주어지면 아무 동작도 하지 않아야 한다.")
	void shouldNotHandleWhenOptionMapIsEmpty() {
		// Given
		Map<Long, List<? extends OptionContextCommand>> emptyProductOptionCommandMap = Map.of();

		// When
		optionDomainHandler.handle(emptyProductOptionCommandMap);

		// Then
		verify(optionGroupHandler, never()).register(any());
		verify(optionDetailHandler, never()).register(any(), any());
		verify(productOptionHandler, never()).register(anyLong(), any(), any(), any());
	}

	@Test
	@DisplayName("handle()에서 productOptionCommandMap이 null이면 아무 동작도 하지 않아야 한다.")
	void shouldNotHandleWhenOptionMapIsNull() {
		// When
		optionDomainHandler.handle(null);

		// Then
		verify(optionGroupHandler, never()).register(any());
		verify(optionDetailHandler, never()).register(any(), any());
		verify(productOptionHandler, never()).register(anyLong(), any(), any(), any());
	}
}
