package com.ryuqq.core.storage.db.exception;

import com.ryuqq.core.enums.ErrorType;

public class DataNotFoundException extends RdsStorageException {

	public DataNotFoundException(String message) {
		super(ErrorType.NOT_FOUND_ERROR, message);
	}

}
