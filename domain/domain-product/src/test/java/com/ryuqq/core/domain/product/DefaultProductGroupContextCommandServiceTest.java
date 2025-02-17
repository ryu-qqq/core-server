package com.ryuqq.core.domain.product;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.core.DefaultProductGroupContextCommandService;
import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.unit.test.BaseUnitTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DefaultProductGroupContextCommandServiceTest extends BaseUnitTest {

	@InjectMocks
	private DefaultProductGroupContextCommandService service;

	@Mock
	private ProductGroupDomainBusinessValidator productGroupDomainBusinessValidator;

	@Mock
	private ProductGroupContextRegister productGroupContextRegister;

	@Mock
	private ProductGroupContextUpdater productGroupContextUpdater;

	@Mock
	private ProductGroupContextEventHandler productGroupContextEventHandler;

	@Mock
	private ProductGroupContextCommand mockCommand;

	@Mock
	private ProductGroupCommand mockProductGroupCommand;

	@Test
	@DisplayName("save()는 ProductGroupContextCommand를 검증하고 등록 후 이벤트를 처리해야 한다.")
	void shouldValidateRegisterAndHandleEventsOnSave() {
		// Given
		long expectedId = 1L;
		when(productGroupContextRegister.registerProductGroupContext(mockCommand)).thenReturn(expectedId);
		when(mockCommand.getProductGroupCommand()).thenReturn(mockProductGroupCommand);

		// When
		long result = service.save(mockCommand);

		// Then
		verify(productGroupDomainBusinessValidator).validate(mockCommand, false);
		verify(productGroupContextRegister).registerProductGroupContext(mockCommand);
		verify(productGroupContextEventHandler).handleEvents(expectedId, mockProductGroupCommand);
		assertEquals(expectedId, result);
	}

	@Test
	@DisplayName("save()에서 검증 실패 시 DomainException이 발생해야 한다.")
	void shouldThrowDomainExceptionIfValidationFailsOnSave() {
		// Given
		doThrow(new DomainException(ErrorType.INVALID_INPUT_ERROR, "Invalid input"))
			.when(productGroupDomainBusinessValidator).validate(mockCommand, false);

		// When & Then
		DomainException exception = assertThrows(DomainException.class, () -> service.save(mockCommand));
		assertTrue(exception.getMessage().contains("Invalid input"));

		verify(productGroupContextRegister, never()).registerProductGroupContext(any());
		verify(productGroupContextEventHandler, never()).handleEvents(anyLong(), any());
	}

	@Test
	@DisplayName("update()는 ProductGroupContextCommand를 검증하고 업데이트 후 이벤트를 처리해야 한다.")
	void shouldValidateUpdateAndHandleEventsOnUpdate() {
		// Given
		long productGroupId = 1L;
		UpdateDecision updateDecision = mock(UpdateDecision.class);
		when(productGroupContextUpdater.updateProductGroupContext(productGroupId, mockCommand))
			.thenReturn(updateDecision);
		when(mockCommand.getProductGroupCommand()).thenReturn(mockProductGroupCommand);

		// When
		long result = service.update(productGroupId, mockCommand);

		// Then
		verify(productGroupDomainBusinessValidator).validate(mockCommand, true);
		verify(productGroupContextUpdater).updateProductGroupContext(productGroupId, mockCommand);
		verify(productGroupContextEventHandler).handleEvents(productGroupId, mockProductGroupCommand, updateDecision);
		assertEquals(productGroupId, result);
	}

	@Test
	@DisplayName("update()에서 검증 실패 시 DomainException 이 발생해야 한다.")
	void shouldThrowDomainExceptionIfValidationFailsOnUpdate() {
		// Given
		long productGroupId = 1L;
		doThrow(new DomainException(ErrorType.INVALID_INPUT_ERROR, "Invalid input"))
			.when(productGroupDomainBusinessValidator).validate(mockCommand, true);

		// When & Then
		DomainException exception = assertThrows(DomainException.class, () -> service.update(productGroupId, mockCommand));
		assertTrue(exception.getMessage().contains("Invalid input"));

		verify(productGroupContextUpdater, never()).updateProductGroupContext(anyLong(), any());
		verify(productGroupContextEventHandler, never()).handleEvents(anyLong(), any(), any());
	}


	@Test
	@DisplayName("update()에서 상품 조회 실패 시 해당 에러가 예상치 못한 에러면 DomainException 이 발생해야 한다.")
	void shouldThrowDomainExceptionIfProductGroupFetchFailOnUpdate() {
		// Given
		long productGroupId = 1L;
		doThrow(new DomainException(ErrorType.UNEXPECTED_ERROR, "test error"))
			.when(productGroupContextUpdater).updateProductGroupContext(anyLong(), any());

		// When & Then
		DomainException exception = assertThrows(DomainException.class, () -> service.update(productGroupId, mockCommand));
		assertTrue(exception.getMessage().contains("test error"));

		verify(productGroupContextEventHandler, never()).handleEvents(anyLong(), any(), any());
	}

	@Test
	@DisplayName("update()에서 상품 조회 실패 시 해당 에러가 상품 조회 실패면 save 메서드를 호출해야 한다.")
	void shouldSaveProductIfProductGroupFetchFailOnUpdate() {
		// Given
		long productGroupId = 1L;
		when(mockCommand.getProductGroupCommand()).thenReturn(mockProductGroupCommand);

		// When & Then
		doThrow(new DomainException(ErrorType.NOT_FOUND_ERROR, "product not found"))
			.when(productGroupContextUpdater).updateProductGroupContext(anyLong(), any());

		when(productGroupContextRegister.registerProductGroupContext(any())).thenReturn(productGroupId);

		long result = service.update(productGroupId, mockCommand);

		// Then
		verify(productGroupDomainBusinessValidator).validate(mockCommand, true);
		verify(productGroupContextUpdater).updateProductGroupContext(productGroupId, mockCommand);
		verify(productGroupDomainBusinessValidator).validate(mockCommand, false);
		verify(productGroupContextRegister).registerProductGroupContext(mockCommand);
		verify(productGroupContextEventHandler).handleEvents(productGroupId, mockProductGroupCommand);
		assertEquals(productGroupId, result);
	}


}
