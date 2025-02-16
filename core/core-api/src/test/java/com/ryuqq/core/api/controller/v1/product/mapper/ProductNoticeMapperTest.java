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

import com.ryuqq.core.api.controller.v1.product.request.ProductNoticeInsertRequestDto;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;
import com.ryuqq.core.enums.Origin;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductNoticeMapperTest extends BaseUnitTest {

	@InjectMocks
	private ProductNoticeMapper productNoticeMapper;

	@BeforeEach
	void setUp() {
		productNoticeMapper = spy(new ProductNoticeMapper());
	}

	@Test
	@DisplayName("supports() 메서드는 ProductNoticeInsertRequestDto 타입을 지원해야 한다.")
	void shouldSupportProductNoticeInsertRequestDto() {
		assertTrue(productNoticeMapper.supports(mock(ProductNoticeInsertRequestDto.class)));
	}

	@Test
	@DisplayName("supports() 메서드는 ProductNoticeInsertRequestDto 이외의 타입을 지원하지 않아야 한다.")
	void shouldNotSupportOtherTypes() {
		assertFalse(productNoticeMapper.supports(new Object()));
	}

	@Test
	@DisplayName("map() 메서드는 ProductNoticeInsertRequestDto를 ProductGroupContextCommandBuilder에 올바르게 매핑해야 한다.")
	void shouldMapProductNoticeInsertRequestDtoToBuilder() {
		// Given
		ProductNoticeInsertRequestDto requestDto = mock(ProductNoticeInsertRequestDto.class);
		when(requestDto.material()).thenReturn("Cotton");
		when(requestDto.color()).thenReturn("Red");
		when(requestDto.size()).thenReturn("L");
		when(requestDto.maker()).thenReturn("ABC Corp");
		when(requestDto.origin()).thenReturn(Origin.KR);
		when(requestDto.washingMethod()).thenReturn("Hand Wash");
		when(requestDto.yearMonth()).thenReturn("2024-01");
		when(requestDto.assuranceStandard()).thenReturn("Standard A");
		when(requestDto.asPhone()).thenReturn("123-456-7890");

		// Given: Create a mock builder
		ProductGroupContextCommandBuilder builder = mock(ProductGroupContextCommandBuilder.class);
		when(builder.getProductGroupId()).thenReturn(java.util.Optional.of(1L));

		// When: Call the map method
		productNoticeMapper.map(requestDto, builder);

		// Then: Verify that withProductNoticeCommand() was called with correct values
		verify(builder).withProductNoticeCommand(argThat(command ->
			command != null &&
				command.productGroupId() == 1L &&
				command.material().equals("Cotton") &&
				command.color().equals("Red") &&
				command.size().equals("L") &&
				command.maker().equals("ABC Corp") &&
				command.origin().equals(Origin.KR) &&
				command.washingMethod().equals("Hand Wash") &&
				command.yearMonth().equals("2024-01") &&
				command.assuranceStandard().equals("Standard A") &&
				command.asPhone().equals("123-456-7890")
		));
	}

}
