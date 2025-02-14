package com.ryuqq.core.api.controller.v1.product.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.ryuqq.core.api.controller.v1.product.request.ProductDetailDescriptionRequestDto;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductDetailDescriptionMapperTest extends BaseUnitTest {

	@InjectMocks
	private ProductDetailDescriptionMapper productDetailDescriptionMapper;


	@BeforeEach
	void setUp() {
		productDetailDescriptionMapper = spy(new ProductDetailDescriptionMapper());
	}


	@Test
	@DisplayName("supports() 메서드는 ProductDetailDescriptionRequestDto 타입을 지원해야 한다.")
	 void shouldSupportProductDetailDescriptionRequestDto() {
		assertTrue(productDetailDescriptionMapper.supports(mock(ProductDetailDescriptionRequestDto.class)));
	}

	@Test
	@DisplayName("supports() 메서드는 ProductDetailDescriptionRequestDto 이외의 타입을 지원하지 않아야 한다.")
	void shouldNotSupportOtherTypes() {
		assertFalse(productDetailDescriptionMapper.supports(new Object()));
	}

	@Test
	@DisplayName("map() 메서드는 ProductDetailDescriptionRequestDto를 ProductGroupContextCommandBuilder에 올바르게 매핑해야 한다.")
	void shouldMapProductDetailDescriptionRequestDtoToBuilder() {
		// Given
		ProductDetailDescriptionRequestDto requestDto = mock(ProductDetailDescriptionRequestDto.class);
		when(requestDto.detailDescription()).thenReturn("This is a detailed description of the product.");

		// Given: Create a mock builder
		ProductGroupContextCommandBuilder builder = mock(ProductGroupContextCommandBuilder.class);
		when(builder.getProductGroupId()).thenReturn(java.util.Optional.of(1L));

		// When: Call the map method
		productDetailDescriptionMapper.map(requestDto, builder);

		// Then: Verify that withProductDetailDescriptionCommand() was called with correct values
		verify(builder).withProductDetailDescriptionCommand(argThat(command ->
			command != null &&
				command.productGroupId() == 1L &&
				command.detailDescription().equals("This is a detailed description of the product.")
		));
	}


}
