package com.ryuqq.core.domain.category.core;

import java.util.List;

import com.ryuqq.core.enums.CategorySearchFiled;
import com.ryuqq.core.enums.CategorySortField;
import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.enums.TargetGroup;

public class CategorySearchCondition {

	private final int page;
	private final int size;
	private final List<Long> categoryIds;
	private final CategoryType categoryType;
	private final TargetGroup targetGroup;
	private final Long cursorId;
	private final CategorySortField categorySortField;
	private final Sort sort;
	private final CategorySearchFiled categorySearchFiled;
	private final String searchWord;


	private CategorySearchCondition(Builder builder) {
		this.page = builder.page;
		this.size = builder.size;
		this.categoryType = builder.categoryType;
		this.targetGroup = builder.targetGroup;
		this.categoryIds = builder.categoryIds;
		this.cursorId = builder.cursorId;
		this.categorySortField = builder.categorySortField;
		this.sort = builder.sort;
		this.categorySearchFiled = builder.categorySearchFiled;
		this.searchWord = builder.searchWord;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private int page = 1;
		private int size = 10;
		private List<Long> categoryIds;
		private CategoryType categoryType;
		private TargetGroup targetGroup;
		private Long cursorId;
		private CategorySortField categorySortField;
		private Sort sort;
		private CategorySearchFiled categorySearchFiled;
		private String searchWord;

		public Builder page(int page) {
			this.page = page;
			return this;
		}

		public Builder size(int size) {
			this.size = size;
			return this;
		}

		public Builder categoryIds(List<Long> brandIds) {
			this.categoryIds = brandIds;
			return this;
		}

		public Builder categoryType(CategoryType categoryType) {
			this.categoryType = categoryType;
			return this;
		}

		public Builder targetGroup(TargetGroup targetGroup) {
			this.targetGroup = targetGroup;
			return this;
		}

		public Builder cursorId(Long cursorId) {
			this.cursorId = cursorId;
			return this;
		}


		public Builder categorySortField(CategorySortField categorySortField) {
			this.categorySortField = categorySortField;
			return this;
		}

		public Builder sort(Sort sort) {
			this.sort = sort;
			return this;
		}

		public Builder categorySearchFiled(CategorySearchFiled categorySearchFiled) {
			this.categorySearchFiled = categorySearchFiled;
			return this;
		}

		public Builder searchWord(String searchWord) {
			this.searchWord = searchWord;
			return this;
		}

		public CategorySearchCondition build() {
			return new CategorySearchCondition(this);
		}
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public TargetGroup getTargetGroup() {
		return targetGroup;
	}

	public Long getCursorId() {
		return cursorId;
	}

	public CategorySortField getCategorySortField() {
		return categorySortField;
	}

	public Sort getSort() {
		return sort;
	}

	public CategorySearchFiled getCategorySearchFiled() {
		return categorySearchFiled;
	}

	public String getSearchWord() {
		return searchWord;
	}
}
