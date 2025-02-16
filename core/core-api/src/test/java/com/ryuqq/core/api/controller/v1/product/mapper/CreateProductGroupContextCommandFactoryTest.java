package com.ryuqq.core.api.controller.v1.product.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.dao.group.DefaultCreateProductGroupContextCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;
import com.ryuqq.core.unit.test.BaseUnitTest;

class CreateProductGroupContextCommandFactoryTest extends BaseUnitTest {

	private CreateProductGroupContextCommandFactory factory;

	@Mock
	private DomainMapper<?> mockMapper;

	@BeforeEach
	void setUp() {
		factory = spy(new CreateProductGroupContextCommandFactory(List.of(mockMapper)));
	}

	@Test
	@DisplayName("createBuilder()는 DefaultCreateProductGroupContextCommand.BuilderGroupContext를 반환해야 한다.")
	void shouldReturnCorrectBuilder() {
		// When
		ProductGroupContextCommandBuilder builder = factory.createBuilder();

		// Then
		assertInstanceOf(DefaultCreateProductGroupContextCommand.BuilderGroupContext.class, builder);
	}
}
