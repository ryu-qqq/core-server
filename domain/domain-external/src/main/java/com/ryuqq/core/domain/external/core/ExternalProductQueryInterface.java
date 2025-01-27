package com.ryuqq.core.domain.external.core;

import java.util.List;

public interface ExternalProductQueryInterface {

	List<? extends ExternalSku> fetchByExternalItemId(String externalItemId);

}
