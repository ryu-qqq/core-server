package com.ryuqq.core.logging;

import java.util.Map;
import java.util.Objects;

import com.ryuqq.core.enums.LogLevel;


/**
 * SQL 로그 엔트리 구현체
 */
public final class SqlLogEntry extends AbstractLogEntry {

	private final String sql;
	private final Map<String, Object> args;
	private final long executionTime;
	private final String errorMessage;
	private final LogLevel logLevel;
	private final String dataSourceName;
	private final String connectionId;
	private final int isolationLevel;
	private final String queryPhase;
	private final int affectedRows;
	private final long threadId;
	private final String dbUser;
	private final String transactionStatus;

	SqlLogEntry(String traceId, String layer, String sql, Map<String, Object> args, long executionTime,
				String errorMessage, LogLevel logLevel, String dataSourceName, String connectionId,
				int isolationLevel, String queryPhase, int affectedRows, long threadId, String dbUser,
				String transactionStatus) {
		super(traceId, layer);
		this.sql = sql;
		this.args = args;
		this.executionTime = executionTime;
		this.errorMessage = errorMessage;
		this.logLevel = logLevel;
		this.dataSourceName = dataSourceName;
		this.connectionId = connectionId;
		this.isolationLevel = isolationLevel;
		this.queryPhase = queryPhase;
		this.affectedRows = affectedRows;
		this.threadId = threadId;
		this.dbUser = dbUser;
		this.transactionStatus = transactionStatus;
	}

	public String getSql() {
		return sql;
	}

	public Map<String, Object> getArgs() {
		return args;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public int getIsolationLevel() {
		return isolationLevel;
	}

	public String getQueryPhase() {
		return queryPhase;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		SqlLogEntry that = (SqlLogEntry) object;
		return executionTime
			== that.executionTime
			&& isolationLevel
			== that.isolationLevel
			&& Objects.equals(sql, that.sql)
			&& Objects.equals(args, that.args)
			&& Objects.equals(errorMessage, that.errorMessage)
			&& logLevel
			== that.logLevel
			&& Objects.equals(dataSourceName, that.dataSourceName)
			&& Objects.equals(connectionId, that.connectionId)
			&& Objects.equals(queryPhase, that.queryPhase);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sql, args, executionTime, errorMessage, logLevel, dataSourceName, connectionId,
			isolationLevel,
			queryPhase);
	}

	@Override
	public String toString() {
		return super.toString() +
			"- SQL: " + sql + "\n" +
			"- Args: " + args + "\n" +
			"- Execution Time: " + executionTime + "ms\n" +
			"- Affected Rows: " + affectedRows + "\n" +
			"- Thread ID: " + threadId + "\n" +
			"- DB User: " + dbUser + "\n" +
			"- Transaction Status: " + transactionStatus + "\n" +
			"- Data Source: " + dataSourceName + "\n" +
			"- Connection ID: " + connectionId + "\n" +
			"- Isolation Level: " + isolationLevel + "\n" +
			"- Query Phase: " + queryPhase + "\n";
	}

}
