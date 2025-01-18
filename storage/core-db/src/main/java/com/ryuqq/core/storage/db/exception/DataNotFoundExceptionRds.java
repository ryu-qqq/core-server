package com.ryuqq.core.storage.db.exception;

import com.ryuqq.core.enums.ErrorType;

public class DataNotFoundExceptionRds extends RdsStorageException {

	public DataNotFoundExceptionRds(String message) {
		super(message, ErrorType.NOT_FOUND_ERROR);
	}

}
