package com.ryuqq.core.domain.external;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.dao.history.DefaultExternalProductSyncCommand;
import com.ryuqq.core.domain.external.dao.history.ExternalProductSyncRequestPersistenceRepository;

@Service
public class ExternalProductSyncRequestRegister {

	private final ExternalProductSyncRequestPersistenceRepository externalProductSyncRequestPersistenceRepository;

	public ExternalProductSyncRequestRegister(
		ExternalProductSyncRequestPersistenceRepository externalProductSyncRequestPersistenceRepository) {
		this.externalProductSyncRequestPersistenceRepository = externalProductSyncRequestPersistenceRepository;
	}

	public void register(long siteId, long productGroupId, String externalProductGroupId, boolean syncResult){
		externalProductSyncRequestPersistenceRepository.save(new DefaultExternalProductSyncCommand(
			MDC.get("traceId"), siteId, productGroupId, externalProductGroupId, syncResult
		));
	}

}
