package com.ryuqq.core.api.controller.v1.product.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextCommandFactory;
import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextCommandFactoryProvider;
import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandInterface;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductGroupContextCommandService 단위 테스트")
class ProductGroupContextCommandServiceTest extends BaseUnitTest {

	@Mock
	private ProductGroupContextCommandFactoryProvider factoryProvider;

	@Mock
	private ProductGroupContextCommandInterface productGroupContextCommandInterface;

	@Mock
	private ProductGroupContextCommandFactory factory;

	@Mock
	private ProductGroupContextCommand productGroupContextCommand;

	@InjectMocks
	private ProductGroupContextCommandService service;


	@Nested
	@DisplayName("registerProductGroupContext 메서드는")
	class RegisterProductGroupContextTests {

		private ProductGroupContextCommandRequestDto requestDto;

		@BeforeEach
		void setUp() {
			requestDto = mock(ProductGroupContextCommandRequestDto.class);
		}

		@Test
		@DisplayName("정상적으로 ProductGroupContextCommand를 생성하고 저장할 수 있다.")
		void shouldRegisterProductGroupContextSuccessfully() {
			// given
			when(factoryProvider.getProvider()).thenReturn(factory);
			when(factory.createCommand(null, requestDto)).thenReturn(productGroupContextCommand);
			when(productGroupContextCommandInterface.save(productGroupContextCommand)).thenReturn(1L);

			// when
			long result = service.registerProductGroupContext(requestDto);

			// then
			Assertions.assertEquals(result, 1L);
			verify(productGroupContextCommandInterface).save(productGroupContextCommand);
		}


		@Test
		@DisplayName("save 과정에서 예외가 발생하면 예외를 던진다.")
		void shouldThrowExceptionWhenSaveFails() {
			// given
			when(factoryProvider.getProvider()).thenReturn(factory);
			when(factory.createCommand(null, requestDto)).thenReturn(productGroupContextCommand);
			when(productGroupContextCommandInterface.save(productGroupContextCommand))
				.thenThrow(new RuntimeException("Test"));

			// when & then
			assertThrows(RuntimeException.class, () ->
				service.registerProductGroupContext(requestDto));
		}
	}

	@Nested
	@DisplayName("updateProductGroupContext 메서드는")
	class UpdateProductGroupContextTests {

		private ProductGroupContextCommandRequestDto requestDto;
		private long productGroupId;

		@BeforeEach
		void setUp() {
			requestDto = mock(ProductGroupContextCommandRequestDto.class);
			productGroupId = 100L;
		}

		@Test
		@DisplayName("정상적으로 ProductGroupContextCommand를 생성하고 업데이트할 수 있다.")
		void shouldUpdateProductGroupContextSuccessfully() {
			// given
			when(factoryProvider.getProvider()).thenReturn(factory);
			when(factory.createCommand(productGroupId, requestDto)).thenReturn(productGroupContextCommand);
			when(productGroupContextCommandInterface.update(productGroupId, productGroupContextCommand)).thenReturn(1L);

			// when
			long result = service.updateProductGroupContext(productGroupId, requestDto);

			// then
			Assertions.assertEquals(result, 1L);
			verify(productGroupContextCommandInterface).update(productGroupId, productGroupContextCommand);
		}

		@Test
		@DisplayName("update 과정에서 다른 예외가 발생하면 예외를 던진다.")
		void shouldThrowExceptionWhenUpdateFails() {
			// given
			when(factoryProvider.getProvider()).thenReturn(factory);
			when(factory.createCommand(productGroupId, requestDto)).thenReturn(productGroupContextCommand);
			when(productGroupContextCommandInterface.update(productGroupId, productGroupContextCommand))
				.thenThrow(new RuntimeException("Test"));

			// when & then
			assertThrows(RuntimeException.class, () ->
				service.updateProductGroupContext(productGroupId, requestDto));
		}
	}

}
