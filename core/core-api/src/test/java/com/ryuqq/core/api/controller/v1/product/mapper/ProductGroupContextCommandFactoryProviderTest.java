package com.ryuqq.core.api.controller.v1.product.mapper;

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

	@InjectMocks
	private ProductGroupContextCommandFactoryProvider provider;

	@Test
	@DisplayName("CreateProductGroupContextCommandFactory를 반환한다.")
	void shouldReturnCreateFactoryWhenIsUpdateIsFalse() {
		// when
		ProductGroupContextCommandFactory result = provider.getProvider();

		// then
		Assertions.assertEquals(result, createFactory);
	}

}
