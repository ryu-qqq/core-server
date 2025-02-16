package com.ryuqq.core.api.controller.v1.product.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;
import com.ryuqq.core.unit.test.BaseUnitTest;

class AbstractProductGroupContextCommandFactoryTest extends BaseUnitTest {

	private TestAbstractProductGroupContextCommandFactory factory;

	@Mock
	private DomainMapper<Object> mockDomainMapper;

	@Mock
	private ProductGroupContextCommandRequestDto dto;

	private static class TestAbstractProductGroupContextCommandFactory extends AbstractProductGroupContextCommandFactory {

		protected TestAbstractProductGroupContextCommandFactory(List<DomainMapper<?>> mappers) {
			super(mappers);
		}

		@Override
		protected Field[] getDeclaredFields(Class<?> clazz) {
			return super.getDeclaredFields(clazz);
		}

		@Override
		protected ProductGroupContextCommandBuilder createBuilder() {
			return mock(ProductGroupContextCommandBuilder.class);
		}
	}

	@BeforeEach
	void setUp() {
		factory = new TestAbstractProductGroupContextCommandFactory(List.of(mockDomainMapper));
	}

	@Test
	@DisplayName("null 필드값이 있을 경우 CoreException을 던진다.")
	void shouldThrowCoreExceptionWhenFieldIsNull() {

		CoreException exception = assertThrows(CoreException.class, () -> {
			factory.createCommand(1L, dto);
		});

		assertTrue(exception.getMessage().contains("Field productGroup cannot be null"));
	}

	@Test
	@DisplayName("필드가 없을 경우 CoreException을 던진다.")
	void shouldThrowCoreExceptionWhenNoFieldsFound() {
		TestAbstractProductGroupContextCommandFactory testFactory = new TestAbstractProductGroupContextCommandFactory(List.of(mockDomainMapper)) {
			@Override
			protected Field[] getDeclaredFields(Class<?> clazz) {
				return new Field[0];
			}
		};

		CoreException exception = assertThrows(CoreException.class, () -> {
			testFactory.createCommand(1L, dto);
		});

		assertTrue(exception.getMessage().contains("ProductGroupContextCommandRequestDto class declaredFields cannot be empty"));
	}

	@Test
	@DisplayName("지원하는 Mapper가 있을 경우, findMapperOrThrow는 해당 Mapper를 반환한다.")
	void shouldFindMapperWhenSupported() {

		String fieldValue = "some value";
		when(mockDomainMapper.supports(fieldValue)).thenReturn(true);

		DomainMapper<Object> mapper = factory.findMapperOrThrow(fieldValue);

		assertNotNull(mapper);
		verify(mockDomainMapper).supports(fieldValue);
	}

	@Test
	@DisplayName("지원하지 않는 Mapper가 없을 경우, findMapperOrThrow는 예외를 던진다.")
	void shouldThrowExceptionWhenNoMapperSupports() {
		String fieldValue = "some unsupported value";
		when(mockDomainMapper.supports(any())).thenReturn(false);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			factory.findMapperOrThrow(fieldValue);
		});

		assertTrue(exception.getMessage().contains("No suitable mapper found for field value"));
	}


}
