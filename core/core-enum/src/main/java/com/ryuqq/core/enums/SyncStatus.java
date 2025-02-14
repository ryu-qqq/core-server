package com.ryuqq.core.enums;

import java.util.List;

public enum SyncStatus {

	WAITING,

	SYNC_REQUIRED,

	PROCESSING,

	APPROVED,

	FAILED;

	public boolean isApproved(){
		return this.equals(APPROVED);
	}


	public static List<SyncStatus> getSyncStatus(){
		return List.of(
			SYNC_REQUIRED, PROCESSING, APPROVED, FAILED
		);
	}

}
