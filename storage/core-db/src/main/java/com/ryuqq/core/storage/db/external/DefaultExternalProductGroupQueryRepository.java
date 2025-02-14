package com.ryuqq.core.storage.db.external;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupQueryRepository;
import com.ryuqq.core.enums.SyncStatus;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class DefaultExternalProductGroupQueryRepository implements ExternalProductGroupQueryRepository {

	private final ExternalProductGroupDomainMapper externalProductGroupDomainMapper;
	private final ExternalProductGroupQueryDslRepository externalProductGroupQueryDslRepository;

	public DefaultExternalProductGroupQueryRepository(
		ExternalProductGroupDomainMapper externalProductGroupDomainMapper, ExternalProductGroupQueryDslRepository externalProductGroupQueryDslRepository) {
		this.externalProductGroupDomainMapper = externalProductGroupDomainMapper;
		this.externalProductGroupQueryDslRepository = externalProductGroupQueryDslRepository;
	}

	@Override
	public boolean existBySiteIdAndProductGroupId(long siteId, long productGroupId) {
		return externalProductGroupQueryDslRepository.existBySiteIdAndProductGroupId(siteId, productGroupId);
	}

	@Override
	public List<ExternalProductGroup> fetchByProductGroupIdsAndStatus(List<Long> productGroupIds, SyncStatus status) {
		return externalProductGroupQueryDslRepository.fetchByProductGroupIdsAndStatus(productGroupIds, status)
			.stream()
			.map(externalProductGroupDomainMapper::toDomain)
			.toList();
	}

	@Override
	public List<ExternalProductGroup> fetchBySiteIdsAndProductGroupIds(List<Long> productGroupIds, List<Long> siteIds) {
		return externalProductGroupQueryDslRepository.fetchBySiteIdsAndProductGroupIds(productGroupIds, siteIds)
			.stream()
			.map(externalProductGroupDomainMapper::toDomain)
			.toList();
	}

	@Override
	public ExternalProductGroup fetchBySiteIdAndProductGroupId(long siteId, long productGroupId) {
		return externalProductGroupQueryDslRepository.fetchBySiteIdAndProductGroupId(productGroupId, siteId)
			.map(externalProductGroupDomainMapper::toDomain)
			.orElseThrow(() -> new DataNotFoundException(
				String.format("External Product Group Not Exist Site Id: %s, Product Group Id : %s", siteId, productGroupId))
			);
	}

	@Override
	public List<ExternalProductGroup> fetchBySiteIdAndStatus(long siteId, SyncStatus status, int size) {
		return externalProductGroupQueryDslRepository.fetchBySiteIdAndStatus(siteId, status, size)
			.stream()
			.map(externalProductGroupDomainMapper::toDomain)
			.toList();

	}
}
