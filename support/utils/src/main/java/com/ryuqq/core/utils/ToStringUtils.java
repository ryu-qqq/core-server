package com.ryuqq.core.utils;

import java.util.Map;

public class ToStringUtils {

	public static String formatParams(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			return "[]";
		}

		StringBuilder formatted = new StringBuilder("[");
		params.forEach((key, value) -> formatted.append(key).append("=").append(value).append(", "));
		if (formatted.length() > 1) {
			formatted.setLength(formatted.length() - 2);
		}
		formatted.append("]");
		return formatted.toString();
	}
}
