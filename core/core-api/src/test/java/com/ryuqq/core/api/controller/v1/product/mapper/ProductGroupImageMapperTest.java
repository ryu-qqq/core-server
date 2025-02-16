package com.ryuqq.core.api.controller.v1.product.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupImageRequestDto;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommand;
import com.ryuqq.core.enums.ProductImageType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupImageMapperTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupImageMapper productGroupImageMapper;

	@BeforeEach
	void setUp() {
		productGroupImageMapper = spy(new ProductGroupImageMapper());
	}

	@Test
	@DisplayName("supports() 메서드는 List<ProductGroupImageRequestDto> 타입을 지원해야 한다.")
	void shouldSupportProductGroupImageRequestDtoList() {
		List<ProductGroupImageRequestDto> requestDtoList = List.of(mock(ProductGroupImageRequestDto.class));
		assertTrue(productGroupImageMapper.supports(requestDtoList));
	}

	@Test
	@DisplayName("supports() 메서드는 비어 있는 리스트 또는 다른 타입의 리스트를 지원하지 않아야 한다.")
	void shouldNotSupportOtherTypes() {
		assertFalse(productGroupImageMapper.supports(List.of()));
		assertFalse(productGroupImageMapper.supports(List.of("String")));
		assertFalse(productGroupImageMapper.supports(new Object()));
	}

	@Test
	@DisplayName("map() 메서드는 List<ProductGroupImageRequestDto>를 ProductGroupContextCommandBuilder에 올바르게 매핑해야 한다.")
	void shouldMapProductGroupImageRequestDtoListToBuilder() {
		// Given
		ProductGroupImageRequestDto image1 = mock(ProductGroupImageRequestDto.class);
		ProductGroupImageRequestDto image2 = mock(ProductGroupImageRequestDto.class);

		when(image1.productImageType()).thenReturn(ProductImageType.MAIN);
		when(image1.imageUrl()).thenReturn("https://example.com/image1.jpg");

		when(image2.productImageType()).thenReturn(ProductImageType.DETAIL);
		when(image2.imageUrl()).thenReturn("https://example.com/image2.jpg");

		List<ProductGroupImageRequestDto> requestDtoList = List.of(image1, image2);

		// Given: Create a mock builder
		ProductGroupContextCommandBuilder builder = mock(ProductGroupContextCommandBuilder.class);
		when(builder.getProductGroupId()).thenReturn(java.util.Optional.of(1L));

		// When: Call the map method
		productGroupImageMapper.map(requestDtoList, builder);

		// Then: Verify that withProductGroupImageContextCommand() was called with correct values
		verify(builder).withProductGroupImageContextCommand(argThat(command -> {
			List<? extends ProductGroupImageCommand> images = command.productGroupImageCommands();
			return images.size() == 2
				&& images.get(0).productGroupId() == 1L
				&& images.get(0).productImageType().equals(ProductImageType.MAIN)
				&& images.get(0).imageUrl().equals("https://example.com/image1.jpg")
				&& images.get(1).productGroupId() == 1L
				&& images.get(1).productImageType().equals(ProductImageType.DETAIL)
				&& images.get(1).imageUrl().equals("https://example.com/image2.jpg");
		}));
	}


}
