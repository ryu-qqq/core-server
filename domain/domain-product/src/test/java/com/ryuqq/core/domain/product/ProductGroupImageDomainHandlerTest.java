package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.core.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.core.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.core.ProductGroupImageContextCommand;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupImageDomainHandlerTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupImageDomainHandler productGroupImageDomainHandler;

	@Mock
	private ProductGroupImageRegister productGroupImageRegister;

	@Mock
	private ProductDetailDescriptionRegister productDetailDescriptionRegister;

	@Mock
	private ProductGroupContextCommand.EssentialProductImageInfo essentialProductImageInfo;

	@Mock
	private ProductGroupImageContextCommand mockProductGroupImageContextCommand;

	@Mock
	private ProductDetailDescriptionCommand mockProductDetailDescriptionCommand;

	@Mock
	private List<ProductGroupImageCommand> mockProductGroupImageCommands;

	@Test
	@DisplayName("handle()은 ProductImage와 DetailDescription을 처리해야 한다.")
	void shouldHandleProductImageAndDetailDescription() {
		// Given
		long productGroupId = 100L;

		when(essentialProductImageInfo.productGroupImageContextCommand()).thenReturn(mockProductGroupImageContextCommand);
		when(essentialProductImageInfo.productDetailDescription()).thenReturn(mockProductDetailDescriptionCommand);

		when(mockProductGroupImageContextCommand.assignProductGroupId(productGroupId)).thenReturn(mockProductGroupImageContextCommand);
		when(mockProductDetailDescriptionCommand.assignProductGroupId(productGroupId)).thenReturn(mockProductDetailDescriptionCommand);

		doReturn(mockProductGroupImageCommands)
			.when(mockProductGroupImageContextCommand)
			.productGroupImageCommands();


		// When
		productGroupImageDomainHandler.handle(productGroupId, essentialProductImageInfo);

		// Then
		verify(productGroupImageRegister).register(mockProductGroupImageCommands);
		verify(productDetailDescriptionRegister).register(mockProductDetailDescriptionCommand);
	}

	@Test
	@DisplayName("handle()에서 productGroupImageRegister.register()가 실패하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProductGroupImageRegisterFails() {
		// Given
		long productGroupId = 100L;

		when(essentialProductImageInfo.productGroupImageContextCommand()).thenReturn(mockProductGroupImageContextCommand);
		when(mockProductGroupImageContextCommand.assignProductGroupId(productGroupId)).thenReturn(mockProductGroupImageContextCommand);

		doReturn(mockProductGroupImageCommands)
			.when(mockProductGroupImageContextCommand)
			.productGroupImageCommands();

		doThrow(new DomainException(ErrorType.UNEXPECTED_ERROR, "Product Image Registration Failed"))
			.when(productGroupImageRegister)
			.register(mockProductGroupImageCommands);

		// When & Then
		assertThrows(DomainException.class, () ->
			productGroupImageDomainHandler.handle(productGroupId, essentialProductImageInfo)
		);

		verify(productGroupImageRegister).register(mockProductGroupImageCommands);
		verify(productDetailDescriptionRegister, never()).register(any());
	}

	@Test
	@DisplayName("handle()에서 productDetailDescriptionRegister.register()가 실패하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProductDetailDescriptionRegisterFails() {
		// Given
		long productGroupId = 100L;

		when(essentialProductImageInfo.productGroupImageContextCommand()).thenReturn(mockProductGroupImageContextCommand);
		when(essentialProductImageInfo.productDetailDescription()).thenReturn(mockProductDetailDescriptionCommand);
		when(mockProductGroupImageContextCommand.assignProductGroupId(productGroupId)).thenReturn(mockProductGroupImageContextCommand);
		when(mockProductDetailDescriptionCommand.assignProductGroupId(productGroupId)).thenReturn(mockProductDetailDescriptionCommand);

		doReturn(mockProductGroupImageCommands)
			.when(mockProductGroupImageContextCommand)
			.productGroupImageCommands();


		doThrow(new DomainException(ErrorType.UNEXPECTED_ERROR, "Product Detail Description Registration Failed"))
			.when(productDetailDescriptionRegister)
			.register(mockProductDetailDescriptionCommand);

		// When & Then
		assertThrows(DomainException.class, () ->
			productGroupImageDomainHandler.handle(productGroupId, essentialProductImageInfo)
		);

		verify(productGroupImageRegister).register(mockProductGroupImageCommands);
		verify(productDetailDescriptionRegister).register(mockProductDetailDescriptionCommand);
	}
}
