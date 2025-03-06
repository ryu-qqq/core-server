package com.ryuqq.core.api.controller.v1.product.mapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupInsertRequestDto;
import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;
import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupMapperTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupMapper productGroupMapper;


	@BeforeEach
	void setUp() {
		productGroupMapper = spy(new ProductGroupMapper());
	}

	@Test
	void shouldSupportProductGroupInsertRequestDto() {
		assertTrue(productGroupMapper.supports(mock(ProductGroupInsertRequestDto.class)));
	}

	@Test
	void shouldNotSupportOtherTypes() {
		assertFalse(productGroupMapper.supports(new Object()));
	}

	@Test
	void shouldMapProductGroupInsertRequestDtoToBuilder() {
		// Given
		ProductGroupInsertRequestDto requestDto = mock(ProductGroupInsertRequestDto.class);
		when(requestDto.productGroupId()).thenReturn(1L);
		when(requestDto.sellerId()).thenReturn(123L);
		when(requestDto.categoryId()).thenReturn(456L);
		when(requestDto.brandId()).thenReturn(789L);
		when(requestDto.productGroupName()).thenReturn("Test Group");
		when(requestDto.styleCode()).thenReturn("STYLE123");
		when(requestDto.productCondition()).thenReturn(ProductCondition.NEW);
		when(requestDto.managementType()).thenReturn(ManagementType.AUTO);
		when(requestDto.optionType()).thenReturn(OptionType.OPTION_ONE);
		when(requestDto.regularPrice()).thenReturn(BigDecimal.valueOf(1000L));
		when(requestDto.currentPrice()).thenReturn(BigDecimal.valueOf(800L));
		when(requestDto.soldOut()).thenReturn(false);
		when(requestDto.displayed()).thenReturn(true);
		when(requestDto.keywords()).thenReturn("Test Keyword");

		// Given: Create a mock builder
		ProductGroupContextCommandBuilder builder = mock(ProductGroupContextCommandBuilder.class);

		// When: Call the map method
		productGroupMapper.map(requestDto, builder);

		verify(builder).withProductGroupCommand(any(ProductGroupCommand.class));
		verify(builder).withOptionType(OptionType.OPTION_ONE);

		// Additional validation to check the ProductGroupCommand mapping
		ProductGroupCommand productGroupCommand = ProductGroupCommand.of(
			1L,
			123L,
			456L,
			789L,
			"Test Group",
			"STYLE123",
			ProductCondition.NEW,
			ManagementType.AUTO,
			OptionType.OPTION_ONE,
			BigDecimal.valueOf(1000L),
			BigDecimal.valueOf(800L),
			BigDecimal.valueOf(800L),
			false,
			true,
			"Test Keyword"
		);

		// Then:
		verify(builder).withProductGroupCommand(argThat(command ->
			command != null &&
				command.id() == 1L &&
				command.sellerId() == 123L &&
				command.categoryId() == 456L &&
				command.brandId() == 789L &&
				command.productGroupName().equals("Test Group") &&
				command.styleCode().equals("STYLE123") &&
				command.productCondition() == ProductCondition.NEW &&
				command.managementType() == ManagementType.AUTO &&
				command.optionType() == OptionType.OPTION_ONE &&
				command.getPrice().getRegularPrice().compareTo(BigDecimal.valueOf(1000L)) == 0 &&
				command.getPrice().getCurrentPrice().compareTo(BigDecimal.valueOf(800L)) == 0 &&
				!command.soldOut() &&
				command.displayed() &&
				command.keyword().equals("Test Keyword")
		));

		verify(builder).withOptionType(OptionType.OPTION_ONE);



	}



}
