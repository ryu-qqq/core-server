package com.ryuqq.core.storage.db.product;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;

import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.storage.db.product.group.QProductGroupEntity;

public class ProductGroupSearchConditionHelper {

	private static final QProductGroupEntity productGroup = QProductGroupEntity.productGroupEntity;

	public static BooleanBuilder buildSearchCondition(ProductGroupSearchCondition condition) {
		BooleanBuilder searchCondition = new BooleanBuilder();

		if (condition.getSearchWord() == null || condition.getSearchWord().isBlank()) {
			return searchCondition;
		}

		String likePattern = "%" + condition.getSearchWord() + "%";

		if (condition.getProductSearchField() != null) {
			switch (condition.getProductSearchField()) {
				case ID:
					List<Long> searchIds = Arrays.stream(condition.getSearchWord().split("\\s+"))
						.map(String::trim)
						.filter(s -> !s.isEmpty()) // 공백 제거
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
						searchCondition.or(productGroup.id.in(searchIds));
					}
					break;

				case PRODUCT_GROUP_NAME:
					searchCondition.or(productGroup.productGroupName.like(likePattern));
					break;
			}
		}

		return searchCondition;
	}
}
