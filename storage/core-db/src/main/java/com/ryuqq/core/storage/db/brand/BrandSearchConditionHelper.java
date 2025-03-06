package com.ryuqq.core.storage.db.brand;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;

import com.ryuqq.core.domain.brand.core.BrandSearchCondition;
import com.ryuqq.core.enums.MainDisplayNameType;

public class BrandSearchConditionHelper {

	private static final QBrandEntity brandEntity = QBrandEntity.brandEntity;

	public static BooleanBuilder buildSearchCondition(BrandSearchCondition condition) {
		BooleanBuilder searchCondition = new BooleanBuilder();

		if (condition.getSearchWord() == null || condition.getSearchWord().isBlank()) {
			return searchCondition;
		}

		String likePattern = "%" + condition.getSearchWord() + "%";

		if (condition.getBrandSearchFiled() != null) {
			switch (condition.getBrandSearchFiled()) {
				case ID:
					List<Long> searchIds = Arrays.stream(condition.getSearchWord().split("\\s+"))
						.map(String::trim)
						.filter(s -> !s.isEmpty())
						.map(s -> {
							try {
								return Long.parseLong(s);
							} catch (NumberFormatException e) {
								return null;
							}
						})
						.filter(Objects::nonNull)
						.collect(Collectors.toList());

					if (!searchIds.isEmpty()) {
						searchCondition.or(brandEntity.id.in(searchIds));
					}
					break;

				case BRAND_NAME:
					Optional.ofNullable(condition.getMainDisplayNameType())
						.ifPresentOrElse(
							mainDisplayNameType -> {
								if (mainDisplayNameType.equals(MainDisplayNameType.KR)) {
									searchCondition.or(brandEntity.brandNameKr.like(likePattern));
								} else {
									searchCondition.or(brandEntity.brandName.like(likePattern));
								}
							},
							() -> {
								throw new IllegalArgumentException("MainDisplayNameType must not be null when searching by BRAND_NAME.");
							}
						);
					break;
			}
		}

		return searchCondition;
	}

}
