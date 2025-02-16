package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.enums.ErrorType;

@Component
public class UpdateDecisionExecutor {

	private final UpdateProcessorProvider processorProvider;

	public UpdateDecisionExecutor(UpdateProcessorProvider processorProvider) {
		this.processorProvider = processorProvider;
	}

	public void execute(UpdateDecision decision) {
		processUpdates(decision.getRealTimeUpdates());
		processUpdates(decision.getBatchUpdates());
	}

	private void processUpdates(List<UpdateDomain<?>> updates) {
		for (UpdateDomain<?> update : updates) {
			Class<?> domainType = update.domain().getClass();

			processorProvider.findProcessor(domainType)
				.ifPresentOrElse(
					processor -> {
						@SuppressWarnings("unchecked")
						UpdateProcessor<Object> castedHandler = (UpdateProcessor<Object>) processor;
						castedHandler.processUpdate(update.domain());
					},
					() -> {
						throw new DomainException(ErrorType.UNEXPECTED_ERROR, "No processor found for domain type: " + domainType);
					}
				);
		}
	}

}
