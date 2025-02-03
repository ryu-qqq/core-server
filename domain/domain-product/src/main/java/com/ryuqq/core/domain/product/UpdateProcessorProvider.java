package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateProcessor;


@Component
public class UpdateProcessorProvider {

	private final List<UpdateProcessor<?>> processors;

	public UpdateProcessorProvider(List<UpdateProcessor<?>> processors) {
		this.processors = processors;
	}

	public Optional<UpdateProcessor<?>> findProcessor(Class<?> domainType) {
		return processors.stream()
			.filter(handler -> handler.supports(domainType))
			.findFirst();
	}

}
