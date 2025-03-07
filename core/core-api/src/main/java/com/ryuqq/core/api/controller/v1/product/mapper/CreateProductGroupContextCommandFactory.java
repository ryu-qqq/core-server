package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.group.DefaultCreateProductGroupContextCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommandBuilder;

@Component
public class CreateProductGroupContextCommandFactory extends AbstractProductGroupContextCommandFactory {

	protected CreateProductGroupContextCommandFactory(List<DomainMapper<?>> mappers) {
		super(mappers);
	}

	@Override
	protected ProductGroupContextCommandBuilder createBuilder() {
		return new DefaultCreateProductGroupContextCommand.BuilderGroupContext();
	}

}
