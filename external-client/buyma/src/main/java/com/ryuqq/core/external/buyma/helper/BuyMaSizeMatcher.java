package com.ryuqq.core.external.buyma.helper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ryuqq.core.domain.external.core.ExternalCategoryOptionMapping;

public class BuyMaSizeMatcher {

	public static long findMatchingOptionId(String size, List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings) {
		return externalCategoryOptionMappings.stream()
			.filter(option -> matchesRange(size, option.getOptionValue()))
			.map(ExternalCategoryOptionMapping::getOptionGroupId)
			.findFirst()
			.orElse(0L);
	}

	private static boolean matchesRange(String size, String externalValue) {
		try {
			if (externalValue == null || externalValue.isEmpty()) {
				return false;
			}

			if (isTextOnly(size) && isTextOnly(externalValue)) {
				return size.equals(externalValue) || externalValue.endsWith("以下") && size.equals(externalValue.replace("以下", "")) || externalValue.endsWith("以上") && size.equals(externalValue.replace("以上", ""));
			}

			Double internalSize = extractNumericPart(size);
			Double externalSize = extractNumericPart(externalValue);
			String condition = extractCondition(externalValue);

			if (internalSize != null && externalSize != null) {
				if ("以下".equals(condition)) {
					return internalSize <= externalSize * 10; // cm to mm 변환
				} else if ("以上".equals(condition)) {
					return internalSize >= externalSize * 10; // cm to mm 변환
				} else {
					return internalSize.equals(externalSize * 10); // 정확히 매칭
				}
			}
			return false;
		} catch (Exception e) {
			throw new IllegalArgumentException(
				String.format("Invalid format for size: %s or externalValue: %s", size, externalValue), e);
		}
	}

	private static boolean isTextOnly(String value) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		return !value.matches(".*\\d.*");
	}

	private static String extractCondition(String value) {
		if (value.contains("以下")) return "以下";
		if (value.contains("以上")) return "以上";
		return "";
	}


	private static Double extractNumericPart(String value) {
		try {
			Pattern pattern = Pattern.compile("([0-9.]+)(cm|mm)?");
			Matcher matcher = pattern.matcher(value);

			if (matcher.find()) {
				String numeric = matcher.group(1);
				String unit = matcher.group(2);

				double size = Double.parseDouble(numeric);


				if (unit == null) {
					if (size >= 20 && size <= 40) { // cm 범위
						return size;
					}
					if (size >= 200 && size <= 400) {
						return size;
					}

				} else if (unit.equals("cm") && size >= 20 && size <= 40) { // cm 범위
					return size;
				} else if (unit.equals("mm") && size >= 200 && size <= 400) { // mm 범위
					return size;
				}
			}
			return null;
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
