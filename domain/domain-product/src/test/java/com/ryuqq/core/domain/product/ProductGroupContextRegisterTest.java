package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupContextRegisterTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupContextRegister productGroupContextRegister;

	@Mock
	private ProductGroupDomainHandler productGroupDomainHandler;

	@Mock
	private ProductGroupImageDomainHandler productGroupImageDomainHandler;

	@Mock
	private ProductDomainHandler productDomainHandler;

	@Mock
	private ProductGroupContextCommand mockCommand;

	private ProductGroupContextCommand.EssentialProductGroupInfo essentialProductGroupInfo;
	private ProductGroupContextCommand.EssentialProductImageInfo essentialProductImageInfo;
	private ProductOptionContextCommand productOptionContextCommand;

	@BeforeEach
	void setUp() {
		essentialProductGroupInfo = mock(ProductGroupContextCommand.EssentialProductGroupInfo.class);
		essentialProductImageInfo = mock(ProductGroupContextCommand.EssentialProductImageInfo.class);
		productOptionContextCommand = mock(ProductOptionContextCommand.class);

	}


	@Test
	@DisplayName("registerProductGroupContext()는 ProductGroupContextCommand를 등록하고 ID를 반환해야 한다.")
	void shouldRegisterProductGroupContextAndReturnId() {
		// Given
		long expectedProductGroupId = 100L;

		when(mockCommand.getEssentialProductGroupInfo()).thenReturn(essentialProductGroupInfo);
		when(mockCommand.getEssentialProductImageInfo()).thenReturn(essentialProductImageInfo);
		when(mockCommand.getProductOptionContextCommand()).thenReturn(productOptionContextCommand);

		when(productGroupDomainHandler.handle(essentialProductGroupInfo)).thenReturn(expectedProductGroupId);

		// When
		long result = productGroupContextRegister.registerProductGroupContext(mockCommand);

		// Then
		verify(productGroupDomainHandler).handle(essentialProductGroupInfo);
		verify(productGroupImageDomainHandler).handle(expectedProductGroupId, essentialProductImageInfo);
		verify(productDomainHandler).handle(expectedProductGroupId, productOptionContextCommand);

		assertEquals(expectedProductGroupId, result);
	}

	@Test
	@DisplayName("registerProductGroupContext()에서 productGroupDomainHandler.handle()이 실패하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProductGroupDomainHandlerFails() {
		// Given
		when(mockCommand.getEssentialProductGroupInfo()).thenReturn(essentialProductGroupInfo);
		when(productGroupDomainHandler.handle(essentialProductGroupInfo))
			.thenThrow(new DomainException(ErrorType.UNEXPECTED_ERROR, "Test"));

		// When & Then
		RuntimeException exception = assertThrows(RuntimeException.class, () ->
			productGroupContextRegister.registerProductGroupContext(mockCommand)
		);

		assertEquals("Test", exception.getMessage());

		verify(productGroupDomainHandler).handle(essentialProductGroupInfo);
		verify(productGroupImageDomainHandler, never()).handle(anyLong(), any());
		verify(productDomainHandler, never()).handle(anyLong(), any());
	}

	@Test
	@DisplayName("registerProductGroupContext()에서 productGroupImageDomainHandler.handle()이 실패하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProductGroupImageDomainHandlerFails() {
		// Given
		long expectedProductGroupId = 100L;

		when(mockCommand.getEssentialProductGroupInfo()).thenReturn(essentialProductGroupInfo);
		when(mockCommand.getEssentialProductImageInfo()).thenReturn(essentialProductImageInfo);
		when(productGroupDomainHandler.handle(essentialProductGroupInfo)).thenReturn(expectedProductGroupId);


		doThrow(new DomainException(ErrorType.UNEXPECTED_ERROR, "Image Handler Failure"))
			.when(productGroupImageDomainHandler)
			.handle(expectedProductGroupId, essentialProductImageInfo);

		// When & Then
		RuntimeException exception = assertThrows(RuntimeException.class, () ->
			productGroupContextRegister.registerProductGroupContext(mockCommand)
		);

		assertEquals("Image Handler Failure", exception.getMessage());

		verify(productGroupDomainHandler).handle(essentialProductGroupInfo);
		verify(productGroupImageDomainHandler).handle(expectedProductGroupId, essentialProductImageInfo);
		verify(productDomainHandler, never()).handle(anyLong(), any());
	}

	@Test
	@DisplayName("registerProductGroupContext()에서 productDomainHandler.handle()이 실패하면 예외를 던져야 한다.")
	void shouldThrowExceptionIfProductDomainHandlerFails() {
		// Given
		long expectedProductGroupId = 100L;

		when(mockCommand.getEssentialProductGroupInfo()).thenReturn(essentialProductGroupInfo);
		when(mockCommand.getEssentialProductImageInfo()).thenReturn(essentialProductImageInfo);
		when(mockCommand.getProductOptionContextCommand()).thenReturn(productOptionContextCommand);

		when(productGroupDomainHandler.handle(essentialProductGroupInfo)).thenReturn(expectedProductGroupId);

		doNothing()
			.when(productGroupImageDomainHandler)
			.handle(expectedProductGroupId, essentialProductImageInfo);


		doThrow(new DomainException(ErrorType.UNEXPECTED_ERROR, "Product Handler Failure"))
			.when(productDomainHandler)
			.handle(expectedProductGroupId, productOptionContextCommand);


		// When & Then
		RuntimeException exception = assertThrows(RuntimeException.class, () ->
			productGroupContextRegister.registerProductGroupContext(mockCommand)
		);

		assertEquals("Product Handler Failure", exception.getMessage());

		verify(productGroupDomainHandler).handle(essentialProductGroupInfo);
		verify(productGroupImageDomainHandler).handle(expectedProductGroupId, essentialProductImageInfo);
		verify(productDomainHandler).handle(expectedProductGroupId, productOptionContextCommand);
	}


}
