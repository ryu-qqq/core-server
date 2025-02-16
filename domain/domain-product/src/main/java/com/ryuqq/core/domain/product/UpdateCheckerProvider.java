package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UpdateCheckerProvider {

	private final List<UpdateChecker<?, ?>> checkers;

	public UpdateCheckerProvider(List<UpdateChecker<?, ?>> checkers) {
		this.checkers = checkers;
	}

	public Optional<UpdateChecker<?, ?>> findChecker(Object fieldValue) {
		return checkers.stream()
			.filter(checker -> checker.supports(fieldValue))
			.findFirst();
	}
}
