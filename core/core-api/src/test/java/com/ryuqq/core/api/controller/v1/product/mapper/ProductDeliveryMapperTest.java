package com.ryuqq.core.api.controller.v1.product.mapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.ryuqq.core.api.controller.v1.product.request.ProductDeliveryRequestDto;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;
import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductDeliveryMapperTest extends BaseUnitTest {

	@InjectMocks
	private ProductDeliveryMapper productDeliveryMapper;

	@BeforeEach
	void setUp() {
		productDeliveryMapper = spy(new ProductDeliveryMapper());
	}

	@Test
	@DisplayName("supports() 메서드는 ProductDeliveryRequestDto 타입을 지원해야 한다.")
	void shouldSupportProductDeliveryRequestDto() {
		assertTrue(productDeliveryMapper.supports(mock(ProductDeliveryRequestDto.class)));
	}

	@Test
	@DisplayName("supports() 메서드는 ProductDeliveryRequestDto 이외의 타입을 지원하지 않아야 한다.")
	void shouldNotSupportOtherTypes() {
		assertFalse(productDeliveryMapper.supports(new Object()));
	}

	@Test
	@DisplayName("map() 메서드는 ProductDeliveryRequestDto를 ProductGroupContextCommandBuilder에 올바르게 매핑해야 한다.")
	void shouldMapProductDeliveryRequestDtoToBuilder() {
		// Given:
		ProductDeliveryRequestDto requestDto = new ProductDeliveryRequestDto(
			"Area",
			BigDecimal.valueOf(100),
			5,
			ReturnMethod.RETURN_SELLER,
			ShipmentCompanyCode.REFER_DETAIL,
			BigDecimal.valueOf(100),
			"Exchange Area"
		);

		ProductGroupContextCommandBuilder builder = mock(ProductGroupContextCommandBuilder.class);
		when(builder.getProductGroupId()).thenReturn(Optional.of(1L));

		// When:
		productDeliveryMapper.map(requestDto, builder);

		// Then:
		verify(builder).withProductDeliveryCommand(argThat(command ->
				command.productGroupId() == 1L &&
				command.deliveryArea().equals("Area") &&
				command.deliveryFee().compareTo(BigDecimal.valueOf(100)) == 0 &&
				command.deliveryPeriodAverage() == 5 &&
				command.returnMethodDomestic() == ReturnMethod.RETURN_SELLER &&
				command.returnCourierDomestic() == ShipmentCompanyCode.REFER_DETAIL &&
				command.returnChargeDomestic().compareTo(BigDecimal.valueOf(100)) == 0 &&
				command.returnExchangeAreaDomestic().equals("Exchange Area")
		));
	}


}
