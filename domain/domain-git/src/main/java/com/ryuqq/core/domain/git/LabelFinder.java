package com.ryuqq.core.domain.git;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.storage.db.git.LabelQueryRepository;

@Component
public class LabelFinder {

	private final LabelQueryRepository labelQueryRepository;

	public LabelFinder(LabelQueryRepository labelQueryRepository) {
		this.labelQueryRepository = labelQueryRepository;
	}

	public List<Label> fetchByNames(List<String> names) {
		return labelQueryRepository.fetchByLabelIn(names).stream()
			.map(l -> new Label(l.getId(), l.getName()))
			.toList();
	}

}
