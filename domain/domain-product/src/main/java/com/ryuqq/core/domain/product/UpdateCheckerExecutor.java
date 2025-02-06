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

	public <T> void executeChecker(UpdateDecision decision, long productGroupId, T existingField, T updatedField) {
		updateCheckerProvider.findChecker(existingField)
			.ifPresent(checker -> {
				@SuppressWarnings("unchecked")
				UpdateChecker<T, T> castedChecker = (UpdateChecker<T, T>) checker;
				decision.merge(castedChecker.checkUpdates(productGroupId, existingField, updatedField));
			});
	}

}
