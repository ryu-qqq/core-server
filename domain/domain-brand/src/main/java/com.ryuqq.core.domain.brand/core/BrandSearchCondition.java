package com.ryuqq.core.domain.brand.core;

import java.util.List;

import com.ryuqq.core.enums.BrandSearchFiled;
import com.ryuqq.core.enums.BrandSortField;
import com.ryuqq.core.enums.MainDisplayNameType;
import com.ryuqq.core.enums.Sort;

public class BrandSearchCondition {

	private final int page;
	private final int size;
	private final List<Long> brandIds;
	private final Long cursorId;
	private final BrandSortField brandSortField;
	private final Sort sort;
	private final MainDisplayNameType mainDisplayNameType;
	private final BrandSearchFiled brandSearchFiled;
	private final String searchWord;

	private BrandSearchCondition(Builder builder) {
		this.page = builder.page;
		this.size = builder.size;
		this.brandIds = builder.brandIds;
		this.cursorId = builder.cursorId;
		this.brandSortField = builder.brandSortField;
		this.sort = builder.sort;
		this.mainDisplayNameType = builder.mainDisplayNameType;
		this.brandSearchFiled = builder.brandSearchFiled;
		this.searchWord = builder.searchWord;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private int page = 1;
		private int size = 10;
		private List<Long> brandIds;
		private Long cursorId;
		private BrandSortField brandSortField;
		private Sort sort;
		private MainDisplayNameType mainDisplayNameType;
		private BrandSearchFiled brandSearchFiled;
		private String searchWord;

		public Builder page(int page) {
			this.page = page;
			return this;
		}

		public Builder size(int size) {
			this.size = size;
			return this;
		}

		public Builder brandIds(List<Long> brandIds) {
			this.brandIds = brandIds;
			return this;
		}

		public Builder cursorId(Long cursorId) {
			this.cursorId = cursorId;
			return this;
		}


		public Builder brandSortField(BrandSortField brandSortField) {
			this.brandSortField = brandSortField;
			return this;
		}

		public Builder sort(Sort sort) {
			this.sort = sort;
			return this;
		}

		public Builder mainDisplayNameType(MainDisplayNameType mainDisplayNameType) {
			this.mainDisplayNameType = mainDisplayNameType;
			return this;
		}

		public Builder brandSearchFiled(BrandSearchFiled brandSearchFiled) {
			this.brandSearchFiled = brandSearchFiled;
			return this;
		}

		public Builder searchWord(String searchWord) {
			this.searchWord = searchWord;
			return this;
		}

		public BrandSearchCondition build() {
			return new BrandSearchCondition(this);
		}
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public List<Long> getBrandIds() {
		return brandIds;
	}

	public Long getCursorId() {
		return cursorId;
	}

	public BrandSortField getBrandSortField() {
		return brandSortField;
	}

	public Sort getSort() {
		return sort;
	}

	public MainDisplayNameType getMainDisplayNameType() {
		return mainDisplayNameType;
	}

	public BrandSearchFiled getBrandSearchFiled() {
		return brandSearchFiled;
	}

	public String getSearchWord() {
		return searchWord;
	}
}
