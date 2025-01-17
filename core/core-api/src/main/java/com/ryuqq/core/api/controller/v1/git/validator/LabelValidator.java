package com.ryuqq.core.api.controller.v1.git.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.Label;
import com.ryuqq.core.domain.git.LabelFinder;

@Component
public class LabelValidator {

	private final LabelFinder labelFinder;

	public LabelValidator(LabelFinder labelFinder) {
		this.labelFinder = labelFinder;
	}

	public LabelValidationResult validate(List<String> labels) {

		List<Label> validLabels = labelFinder.fetchByNames(labels);

		List<String> invalidLabels = labels.stream()
			.filter(label -> validLabels.stream().noneMatch(validLabel -> validLabel.name().equals(label)))
			.toList();

		return new LabelValidationResult(invalidLabels);
	}





}
