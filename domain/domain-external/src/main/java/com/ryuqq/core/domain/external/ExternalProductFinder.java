package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.core.ExternalProductQueryInterface;
import com.ryuqq.core.domain.external.core.ExternalSku;
import com.ryuqq.core.domain.external.dao.options.ExternalProductQueryRepository;


@Component
public class ExternalProductFinder implements ExternalProductQueryInterface {

	private final ExternalProductQueryRepository externalProductQueryRepository;

	public ExternalProductFinder(ExternalProductQueryRepository externalProductQueryRepository) {
		this.externalProductQueryRepository = externalProductQueryRepository;
	}

	public List<ExternalProduct> fetchByExternalProductGroupId(String externalProductGroupId) {
		return externalProductQueryRepository.fetchByExternalProductGroupId(externalProductGroupId);
	}

	public List<ExternalProduct> fetchByExternalProductGroupIds(List<String> externalProductGroupIds) {
		return externalProductQueryRepository.fetchByExternalProductGroupIds(externalProductGroupIds);
	}

	@Override
	public List<? extends ExternalSku> fetchByExternalItemId(String externalItemId) {
		return fetchByExternalProductGroupId(externalItemId);
	}
}
