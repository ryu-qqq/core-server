package com.ryuqq.core.domain.external;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupStatusRepository;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.enums.SyncStatus;

@Component
public class ExternalProductGroupStatusUpdater {

	private final ExternalSiteFinder externalSiteFinder;
	private final ExternalProductGroupStatusRepository externalProductGroupStatusRepository;

	public ExternalProductGroupStatusUpdater(ExternalSiteFinder externalSiteFinder,
											 ExternalProductGroupStatusRepository externalProductGroupStatusRepository) {
		this.externalSiteFinder = externalSiteFinder;
		this.externalProductGroupStatusRepository = externalProductGroupStatusRepository;
	}

	public long updateStatus(Long productGroupId, SiteName siteName, String externalProductGroupId, SyncStatus syncStatus){
		ExternalSite externalSite = externalSiteFinder.fetchBySiteName(siteName);

		return externalProductGroupStatusRepository.updateExternalProductGroupStatus(
			productGroupId,
			externalSite.siteId(),
			externalProductGroupId,
			syncStatus);
	}

}
