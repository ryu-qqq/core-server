package com.ryuqq.core.storage.db.exception;

public class DataNotFoundException extends StorageException {

	public DataNotFoundException(String message) {
		super(404, message);
	}

}
