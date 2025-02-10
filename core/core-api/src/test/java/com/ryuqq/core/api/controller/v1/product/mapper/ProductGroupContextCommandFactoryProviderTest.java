package com.ryuqq.core.api.controller.v1.product.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductGroupContextCommandFactoryProvider 단위 테스트")
class ProductGroupContextCommandFactoryProviderTest extends BaseUnitTest {

	@Mock
	private CreateProductGroupContextCommandFactory createFactory;

	@Mock
	private UpdateProductGroupContextCommandFactory updateFactory;

	@InjectMocks
	private ProductGroupContextCommandFactoryProvider provider;

	@Test
	@DisplayName("isUpdate가 false이면 CreateProductGroupContextCommandFactory를 반환한다.")
	void shouldReturnCreateFactoryWhenIsUpdateIsFalse() {
		// when
		ProductGroupContextCommandFactory result = provider.getProvider(false);

		// then
		Assertions.assertEquals(result, createFactory);
	}

	@Test
	@DisplayName("isUpdate가 true이면 UpdateProductGroupContextCommandFactory를 반환한다.")
	void shouldReturnUpdateFactoryWhenIsUpdateIsTrue() {
		// when
		ProductGroupContextCommandFactory result = provider.getProvider(true);

		// then
		Assertions.assertEquals(result, updateFactory);
	}
}
