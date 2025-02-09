package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.DefaultUpdateProductGroupContextCommand;
import com.ryuqq.core.domain.product.core.ProductGroupContextCommandBuilder;

@Component
public class UpdateProductGroupContextCommandFactory extends AbstractProductGroupContextCommandFactory {

	protected UpdateProductGroupContextCommandFactory(List<DomainMapper<?>> mappers) {
		super(mappers);
	}

	@Override
	protected ProductGroupContextCommandBuilder createBuilder() {
		return new DefaultUpdateProductGroupContextCommand.Builder();
	}

}
