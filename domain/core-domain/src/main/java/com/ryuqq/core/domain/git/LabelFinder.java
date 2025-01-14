package com.ryuqq.core.domain.git;

import com.ryuqq.core.storage.db.git.LabelQueryRepository;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LabelFinder {

	private final LabelQueryRepository labelQueryRepository;

	public LabelFinder(LabelQueryRepository labelQueryRepository) {
		this.labelQueryRepository = labelQueryRepository;
	}

	public List<Label> fetchByNames(List<String> names) {
		return labelQueryRepository.fetchByLabels(names).stream()
			.map(l -> new Label(l.getName()))
			.toList();
	}
}
