package com.ryuqq.core.api.controller.v1.git.validator;

import com.ryuqq.core.api.controller.v1.git.request.GitMergeEventRequestDto;
import com.ryuqq.core.domain.git.Label;
import com.ryuqq.core.domain.git.LabelFinder;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LabelValidator {

	private final LabelFinder labelFinder;

	public LabelValidator(LabelFinder labelFinder) {
		this.labelFinder = labelFinder;
	}

	public LabelValidationResult validate(List<GitMergeEventRequestDto.Label> labels) {
		List<String> requestLabelNames = labels.stream()
			.map(GitMergeEventRequestDto.Label::name)
			.toList();

		List<Label> validLabels = labelFinder.fetchByNames(requestLabelNames);

		List<String> invalidLabels = requestLabelNames.stream()
			.filter(label -> validLabels.stream().noneMatch(validLabel -> validLabel.name().equals(label)))
			.toList();

		return new LabelValidationResult(invalidLabels);
	}





}
