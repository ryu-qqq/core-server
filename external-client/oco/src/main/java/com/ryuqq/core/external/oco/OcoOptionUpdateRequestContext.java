package com.ryuqq.core.external.oco;

import java.util.List;

public record OcoOptionUpdateRequestContext(
	List<OcoOptionContext> updatedOcoOptionContexts,
	List<OcoOptionContext> deletedOcoOptionContexts

) {

	public boolean isDeleteRequest() {
		return !deletedOcoOptionContexts.isEmpty();
	}

}
