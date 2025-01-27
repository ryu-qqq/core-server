package com.ryuqq.core.storage.db.exception;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.logging.SqlLogEntry;

public class SlowQueryException extends RdsStorageException {

	private final SqlLogEntry sqlLogEntry;

	public SlowQueryException(ErrorType errorType, SqlLogEntry sqlLogEntry) {
		super(errorType, errorType.getMessage());
		this.sqlLogEntry = sqlLogEntry;
	}

	public SqlLogEntry getSqlLogEntry() {
		return sqlLogEntry;
	}

}
