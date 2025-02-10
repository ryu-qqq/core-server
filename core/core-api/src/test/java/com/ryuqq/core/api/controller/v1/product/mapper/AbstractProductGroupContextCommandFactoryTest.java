package com.ryuqq.core.api.controller.v1.product.mapper;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.unit.test.BaseUnitTest;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("AbstractProductGroupContextCommandFactory 단위 테스트")
class AbstractProductGroupContextCommandFactoryTest extends BaseUnitTest {

	// @Mock
	// private DomainMapper<Object> domainMapper;
	//
	// @Mock
	// private ProductGroupContextCommandBuilder builder;
	//
	// @InjectMocks
	// private TestProductGroupContextCommandFactory factory;
	//
	// private ProductGroupContextCommandRequestDto requestDto;
	//
	// @BeforeEach
	// void setUp() {
	// 	requestDto = mock(ProductGroupContextCommandRequestDto.class);
	// }
	//
	// @Test
	// @DisplayName("DTO 필드가 올바르게 매핑되어 Command 객체가 생성된다.")
	// void shouldCreateCommandSuccessfully() throws Exception {
	// 	// given
	// 	Field testField = ProductGroupContextCommandRequestDto.class.getDeclaredField("testField");
	// 	testField.setAccessible(true);
	// 	testField.set(requestDto, "Test Value");
	//
	// 	when(domainMapper.supports("Test Value")).thenReturn(true);
	// 	when(factory.findMapperOrThrow("Test Value")).thenReturn(domainMapper);
	//
	// 	// when
	// 	ProductGroupContextCommand result = factory.createCommand(1L, requestDto);
	//
	// 	// then
	// 	verify(domainMapper).map("Test Value", builder);
	// 	assertNotNull(result);
	// }
	//
	// @Test
	// @DisplayName("매퍼가 없는 경우 IllegalArgumentException이 발생한다.")
	// void shouldThrowExceptionWhenNoSuitableMapperFound() {
	// 	// given
	// 	when(factory.findMapperOrThrow(any())).thenThrow(new IllegalArgumentException("No suitable mapper found"));
	//
	// 	// when & then
	// 	IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
	// 		factory.createCommand(1L, requestDto));
	//
	// 	assertEquals("No suitable mapper found", exception.getMessage());
	// }
	//
	// @Test
	// @DisplayName("필드 접근 불가 시 CoreException이 발생한다.")
	// void shouldThrowCoreExceptionWhenIllegalAccess() throws Exception {
	// 	// given
	// 	Field testField = ProductGroupContextCommandRequestDto.class.getDeclaredField("testField");
	// 	testField.setAccessible(false);
	//
	// 	// when & then
	// 	CoreException exception = assertThrows(CoreException.class, () ->
	// 		factory.createCommand(1L, requestDto));
	//
	// 	assertEquals(ErrorType.UNEXPECTED_ERROR, exception.getErrorType());
	// }
	//
	//
	//
	//
	//
	// private static class TestProductGroupContextCommandFactory extends AbstractProductGroupContextCommandFactory {
	// 	protected TestProductGroupContextCommandFactory(List<DomainMapper<?>> mappers) {
	// 		super(mappers);
	// 	}
	//
	// 	@Override
	// 	protected ProductGroupContextCommandBuilder createBuilder() {
	// 		return mock(ProductGroupContextCommandBuilder.class);
	// 	}
	// }

}
