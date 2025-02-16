package com.ryuqq.core.api.controller.v1.product.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.ryuqq.core.api.controller.v1.product.request.ProductInsertRequestDto;
import com.ryuqq.core.api.controller.v1.product.request.ProductOptionInsertRequestDto;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductMapperTest extends BaseUnitTest {

	@InjectMocks
	private ProductMapper productMapper;

	@BeforeEach
	void setUp() {
		productMapper = spy(new ProductMapper());
	}

	@Test
	@DisplayName("supports() 메서드는 List<ProductInsertRequestDto> 타입을 지원해야 한다.")
	void shouldSupportProductInsertRequestDtoList() {
		List<ProductInsertRequestDto> requestDtoList = List.of(mock(ProductInsertRequestDto.class));
		assertTrue(productMapper.supports(requestDtoList));
	}

	@Test
	@DisplayName("supports() 메서드는 비어 있는 리스트 또는 다른 타입의 리스트를 지원하지 않아야 한다.")
	void shouldNotSupportOtherTypes() {
		assertFalse(productMapper.supports(List.of()));
		assertFalse(productMapper.supports(List.of("String")));
		assertFalse(productMapper.supports(new Object()));
	}

	@Test
	@DisplayName("map() 메서드는 List<ProductInsertRequestDto>를 ProductGroupContextCommandBuilder에 올바르게 매핑해야 한다.")
	void shouldMapProductInsertRequestDtoListToBuilder() {
		// Given
		ProductOptionInsertRequestDto option1 = mock(ProductOptionInsertRequestDto.class);
		ProductOptionInsertRequestDto option2 = mock(ProductOptionInsertRequestDto.class);

		when(option1.optionName()).thenReturn(OptionName.SIZE);
		when(option1.optionValue()).thenReturn("Large");

		when(option2.optionName()).thenReturn(OptionName.COLOR);
		when(option2.optionValue()).thenReturn("Red");

		ProductInsertRequestDto product1 = mock(ProductInsertRequestDto.class);
		ProductInsertRequestDto product2 = mock(ProductInsertRequestDto.class);

		when(product1.productId()).thenReturn(101L);
		when(product1.soldOut()).thenReturn(false);
		when(product1.displayed()).thenReturn(true);
		when(product1.quantity()).thenReturn(10);
		when(product1.additionalPrice()).thenReturn(BigDecimal.valueOf(100));
		when(product1.options()).thenReturn(List.of(option1, option2));

		when(product2.productId()).thenReturn(102L);
		when(product2.soldOut()).thenReturn(true);
		when(product2.displayed()).thenReturn(false);
		when(product2.quantity()).thenReturn(5);
		when(product2.additionalPrice()).thenReturn(BigDecimal.valueOf(50));
		when(product2.options()).thenReturn(List.of(option1));

		List<ProductInsertRequestDto> requestDtoList = List.of(product1, product2);

		// Given: Create a mock builder
		ProductGroupContextCommandBuilder builder = mock(ProductGroupContextCommandBuilder.class);
		when(builder.getProductGroupId()).thenReturn(java.util.Optional.of(1L));
		when(builder.getOptionType()).thenReturn(OptionType.OPTION_ONE);

		// When: Call the map method
		productMapper.map(requestDtoList, builder);

		// Then: Verify that withProductContextCommand() was called with correct values
		verify(builder).withProductContextCommand(argThat(command -> {
			List<? extends ProductOptionCommand> products = command.productCommands();
			return command.productGroupId() == 1L
				&& command.optionType() == OptionType.OPTION_ONE
				&& products.size() == 2
				&& products.get(0).productCommand().id() == 101L
				&& !products.get(0).productCommand().soldOut()
				&& products.get(0).productCommand().displayed()
				&& products.get(0).productCommand().quantity() == 10
				&& products.get(0).productCommand().additionalPrice().compareTo(BigDecimal.valueOf(100)) == 0
				&& products.get(1).productCommand().id() == 102L
				&& products.get(1).productCommand().soldOut()
				&& !products.get(1).productCommand().displayed()
				&& products.get(1).productCommand().quantity() == 5
				&& products.get(1).productCommand().additionalPrice().compareTo(BigDecimal.valueOf(50)) == 0;
		}));
	}
}
