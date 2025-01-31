package com.ryuqq.core.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 로그 타입별로 특정 로거를 사용하는 Loggers 클래스
 */
public class Loggers {
	public static final Logger JSON_LOGGER = LoggerFactory.getLogger("ASYNC_JSON");
	public static final Logger ERROR_LOGGER = LoggerFactory.getLogger("ASYNC_ERROR");
	public static final Logger SLOW_QUERY_LOGGER = LoggerFactory.getLogger("ASYNC_SLOW_QUERY");

}
