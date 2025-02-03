package com.ryuqq.core.domain.external.dao.group;


import java.util.List;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.SyncStatus;

public interface ExternalProductGroupQueryRepository {

	List<ExternalProductGroup> fetchByProductGroupIdsAndStatus(List<Long> productGroupIds, SyncStatus status);
	List<ExternalProductGroup> fetchBySiteIdsAndProductGroupIds(List<Long> productGroupIds, List<Long> siteIds);

	ExternalProductGroup fetchBySiteIdAndProductGroupId(long siteId, long productGroupId);
	List<ExternalProductGroup> fetchBySiteIdAndStatus(long siteId, SyncStatus status);

}
