package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;

@Component
public class UpdateCheckerExecutor {

	private final UpdateCheckerProvider updateCheckerProvider;

	public UpdateCheckerExecutor(UpdateCheckerProvider updateCheckerProvider) {
		this.updateCheckerProvider = updateCheckerProvider;
	}

	public <T, U> void executeChecker(UpdateDecision decision, T existingField, U updatedField) {
		updateCheckerProvider.findChecker(existingField)
			.ifPresent(checker -> {
				@SuppressWarnings("unchecked")
				UpdateChecker<T, U> castedChecker = (UpdateChecker<T, U>) checker;
				castedChecker.checkUpdates(decision, existingField, updatedField);
			});
	}

}
