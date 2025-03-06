package com.ryuqq.core.storage.db.category;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;

import com.ryuqq.core.domain.category.core.CategorySearchCondition;

public class CategorySearchConditionHelper {

	private static final QCategoryEntity categoryEntity = QCategoryEntity.categoryEntity;

	public static BooleanBuilder buildSearchCondition(CategorySearchCondition condition) {
		BooleanBuilder searchCondition = new BooleanBuilder();

		if (condition.getSearchWord() == null || condition.getSearchWord().isBlank()) {
			return searchCondition;
		}

		String likePattern = "%" + condition.getSearchWord() + "%";

		if (condition.getCategorySearchFiled() != null) {
			switch (condition.getCategorySearchFiled()) {
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
						searchCondition.or(categoryEntity.id.in(searchIds));
					}
					break;

				case CATEGORY_NAME:
					searchCondition.or(categoryEntity.categoryName.like(likePattern));
					break;
			}
		}

		return searchCondition;
	}


}
