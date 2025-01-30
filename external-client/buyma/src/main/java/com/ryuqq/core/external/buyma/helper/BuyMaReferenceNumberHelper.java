package com.ryuqq.core.external.buyma.helper;

public class BuyMaReferenceNumberHelper {


	public static String getDefaultReferenceNumber(String styleCode, long productGroupId) {
		StringBuilder sb = new StringBuilder();
		sb.append(productGroupId);

		if (styleCode != null && !styleCode.isEmpty()) {
			sb.append("_");
			sb.append(styleCode);
		}
		return sb.toString();
	}
}
