package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupQueryRepository;
import com.ryuqq.core.enums.SyncStatus;

@Component
public class ExternalProductGroupFinder {

	public ExternalProductGroupFinder(ExternalProductGroupQueryRepository externalProductGroupQueryRepository) {
		this.externalProductGroupQueryRepository = externalProductGroupQueryRepository;
	}

	private final ExternalProductGroupQueryRepository externalProductGroupQueryRepository;

	public boolean existBySiteIdAndProductGroupId(long siteId, long productGroupId) {
		return externalProductGroupQueryRepository.existBySiteIdAndProductGroupId(siteId, productGroupId);

	}

	public ExternalProductGroup fetchBySiteIdAndProductGroupId(long siteId, long productGroupId) {
		return externalProductGroupQueryRepository.fetchBySiteIdAndProductGroupId(siteId, productGroupId);
	}

	public List<ExternalProductGroup> fetchBySiteIdAndStatus(long siteId, SyncStatus status, int size) {
		return externalProductGroupQueryRepository.fetchBySiteIdAndStatus(siteId, status, size);
	}

	public List<ExternalProductGroup> fetchByProductGroupId(long productGroupId) {
		return externalProductGroupQueryRepository.fetchByProductGroupIdsAndStatus(List.of(productGroupId), null);
	}

	public List<ExternalProductGroup> fetchByProductGroupIdsAndSiteIds(long productGroupId, List<Long> siteIds, List<SyncStatus> statuses) {
		return externalProductGroupQueryRepository.fetchBySiteIdsAndProductGroupIds(List.of(productGroupId), siteIds);
	}

	public List<ExternalProductGroup> fetchByProductGroupIds(List<Long> productGroupIds) {
		return externalProductGroupQueryRepository.fetchByProductGroupIdsAndStatus(productGroupIds, null);
	}

}
