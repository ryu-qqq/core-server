package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.core.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.core.ProductGroupCommand;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.core.ProductNoticeCommand;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupDomainHandlerTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupDomainHandler productGroupDomainHandler;

	@Mock
	private ProductGroupRegister productGroupRegister;

	@Mock
	private ProductDeliveryRegister productDeliveryRegister;

	@Mock
	private ProductNoticeRegister productNoticeRegister;

	@Mock
	private ProductGroupContextCommand.EssentialProductGroupInfo essentialProductGroupInfo;

	@Mock
	private ProductGroupCommand mockProductGroupCommand;

	@Mock
	private ProductDeliveryCommand mockProductDeliveryCommand;

	@Mock
	private ProductNoticeCommand mockProductNoticeCommand;

	@Test
	@DisplayName("handle()은 ProductGroupContextCommand의 필드를 개별적으로 처리하고 productGroupId를 반환해야 한다.")
	void shouldHandleEssentialProductGroupInfoAndReturnProductGroupId() {
		// Given
		long expectedProductGroupId = 100L;

		when(essentialProductGroupInfo.productGroupCommand()).thenReturn(mockProductGroupCommand);
		when(essentialProductGroupInfo.productDeliveryCommand()).thenReturn(mockProductDeliveryCommand);
		when(essentialProductGroupInfo.productNoticeCommand()).thenReturn(mockProductNoticeCommand);

		when(productGroupRegister.register(mockProductGroupCommand)).thenReturn(expectedProductGroupId);
		when(mockProductDeliveryCommand.assignProductGroupId(expectedProductGroupId))
			.thenReturn(mockProductDeliveryCommand);
		when(mockProductNoticeCommand.assignProductGroupId(expectedProductGroupId))
			.thenReturn(mockProductNoticeCommand);

		// When
		long result = productGroupDomainHandler.handle(essentialProductGroupInfo);

		// Then
		verify(productGroupRegister).register(mockProductGroupCommand);
		verify(productDeliveryRegister).register(mockProductDeliveryCommand);
		verify(productNoticeRegister).register(mockProductNoticeCommand);
		assertEquals(expectedProductGroupId, result);
	}

	@Test
	@DisplayName("handle()에서 productGroupRegister.register()가 실패하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProductGroupRegisterFails() {
		// Given
		when(essentialProductGroupInfo.productGroupCommand()).thenReturn(mockProductGroupCommand);
		when(productGroupRegister.register(mockProductGroupCommand))
			.thenThrow(new RuntimeException("Product Group Registration Failed"));

		// When & Then
		RuntimeException exception = assertThrows(RuntimeException.class, () ->
			productGroupDomainHandler.handle(essentialProductGroupInfo)
		);

		assertEquals("Product Group Registration Failed", exception.getMessage());

		verify(productGroupRegister).register(mockProductGroupCommand);
		verify(productDeliveryRegister, never()).register(any());
		verify(productNoticeRegister, never()).register(any());
	}

	@Test
	@DisplayName("handle()에서 productDeliveryRegister.register()가 실패하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProductDeliveryRegisterFails() {
		// Given
		long expectedProductGroupId = 100L;

		when(essentialProductGroupInfo.productGroupCommand()).thenReturn(mockProductGroupCommand);
		when(essentialProductGroupInfo.productDeliveryCommand()).thenReturn(mockProductDeliveryCommand);

		when(productGroupRegister.register(mockProductGroupCommand)).thenReturn(expectedProductGroupId);
		when(mockProductDeliveryCommand.assignProductGroupId(expectedProductGroupId))
			.thenReturn(mockProductDeliveryCommand);

		doThrow(new DomainException(ErrorType.UNEXPECTED_ERROR, "Product Delivery Registration Failed"))
			.when(productDeliveryRegister)
			.register(mockProductDeliveryCommand);


		// When & Then
		RuntimeException exception = assertThrows(RuntimeException.class, () ->
			productGroupDomainHandler.handle(essentialProductGroupInfo)
		);

		assertEquals("Product Delivery Registration Failed", exception.getMessage());

		verify(productGroupRegister).register(mockProductGroupCommand);
		verify(productDeliveryRegister).register(mockProductDeliveryCommand);
		verify(productNoticeRegister, never()).register(any());
	}

	@Test
	@DisplayName("handle()에서 productNoticeRegister.register()가 실패하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProductNoticeRegisterFails() {
		// Given
		long expectedProductGroupId = 100L;

		when(essentialProductGroupInfo.productGroupCommand()).thenReturn(mockProductGroupCommand);
		when(essentialProductGroupInfo.productDeliveryCommand()).thenReturn(mockProductDeliveryCommand);
		when(essentialProductGroupInfo.productNoticeCommand()).thenReturn(mockProductNoticeCommand);

		when(productGroupRegister.register(mockProductGroupCommand)).thenReturn(expectedProductGroupId);
		when(mockProductDeliveryCommand.assignProductGroupId(expectedProductGroupId))
			.thenReturn(mockProductDeliveryCommand);
		when(mockProductNoticeCommand.assignProductGroupId(expectedProductGroupId))
			.thenReturn(mockProductNoticeCommand);


		doThrow(new DomainException(ErrorType.UNEXPECTED_ERROR, "Product Notice Registration Failed"))
			.when(productNoticeRegister)
			.register(mockProductNoticeCommand);

		// When & Then
		RuntimeException exception = assertThrows(RuntimeException.class, () ->
			productGroupDomainHandler.handle(essentialProductGroupInfo)
		);

		assertEquals("Product Notice Registration Failed", exception.getMessage());

		verify(productGroupRegister).register(mockProductGroupCommand);
		verify(productDeliveryRegister).register(mockProductDeliveryCommand);
		verify(productNoticeRegister).register(mockProductNoticeCommand);
	}
}
