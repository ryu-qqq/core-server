package com.ryuqq.core.storage.db.git;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.git.Label;
import com.ryuqq.core.domain.git.LabelQueryRepository;

@Repository
public class DefaultLabelQueryRepository implements LabelQueryRepository {

	private final LabelQueryDslRepository labelQueryDslRepository;

	public DefaultLabelQueryRepository(LabelQueryDslRepository labelQueryDslRepository) {
		this.labelQueryDslRepository = labelQueryDslRepository;
	}

	@Override
	public List<Label> fetchByLabelIn(List<String> names) {
		return labelQueryDslRepository.fetchByLabelIn(names).stream()
			.map(l -> new Label(l.getId(), l.getName()))
			.toList();
	}
}
