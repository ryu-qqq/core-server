package com.ryuqq.core.enums;

public enum SyncStatus {

	WAITING,

	SYNC_REQUIRED,

	PROCESSING,

	APPROVED,

	FAILED;

	public boolean isApproved(){
		return this.equals(APPROVED);
	}

}
