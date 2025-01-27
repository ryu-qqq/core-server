package com.ryuqq.core.storage.db.external;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.ExternalProduct;
import com.ryuqq.core.domain.external.dao.options.ExternalProductQueryRepository;

@Repository
public class DefaultExternalProductQueryRepository implements ExternalProductQueryRepository {


	private final ExternalProductQueryDslRepository externalProductQueryDslRepository;

	public DefaultExternalProductQueryRepository(ExternalProductQueryDslRepository externalProductQueryDslRepository) {
		this.externalProductQueryDslRepository = externalProductQueryDslRepository;
	}

	@Override
	public List<ExternalProduct> fetchByExternalProductGroupId(String externalProductGroupId) {
		return fetchByExternalProductGroupIds(List.of(externalProductGroupId));
	}

	@Override
	public List<ExternalProduct> fetchByExternalProductGroupIds(List<String> externalProductGroupIds) {
		return externalProductQueryDslRepository.fetchByExternalProductGroupIds(externalProductGroupIds).stream()
			.map(e -> ExternalProduct.create(
				e.getId(),
				e.getExternalProductGroupId(),
				e.getExternalProductId(),
				e.getProductId(),
				e.getOptionValue(),
				e.getQuantity(),
				e.getAdditionalPrice(),
				e.isSoldOut(),
				e.isDisplayed()
			))
			.toList();
	}



}
