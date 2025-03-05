package com.ryuqq.core.domain.product.core;

import java.time.LocalDateTime;
import java.util.List;

import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.ProductSearchField;
import com.ryuqq.core.enums.ProductSortField;
import com.ryuqq.core.enums.Sort;

public class ProductGroupSearchCondition {

	private final int page;
	private final int size;
	private final List<Long> sellerIds;
	private final List<Long> productGroupIds;
	private final List<Long> categoryIds;
	private final List<Long> brandIds;
	private final ManagementType managementType;
	private final Boolean displayed;
	private final Boolean soldOut;
	private final Long minSalePrice;
	private final Long maxSalePrice;
	private final Long minDiscountRate;
	private final Long maxDiscountRate;
	private final Sort sort;
	private final ProductSortField productSortField;
	private final Long cursorId;
	private final ProductSearchField productSearchField;
	private final String searchWord;
	private final LocalDateTime createdAtFrom;
	private final LocalDateTime createdAtTo;
	private final LocalDateTime updatedAtFrom;
	private final LocalDateTime updatedAtTo;
	private final boolean simpleQuery;

	private ProductGroupSearchCondition(Builder builder) {
		this.page = builder.page;
		this.size = builder.size;
		this.sellerIds = builder.sellerIds;
		this.productGroupIds = builder.productGroupIds;
		this.categoryIds = builder.categoryIds;
		this.brandIds = builder.brandIds;
		this.managementType = builder.managementType;
		this.displayed = builder.displayed;
		this.soldOut = builder.soldOut;
		this.minSalePrice = builder.minSalePrice;
		this.maxSalePrice = builder.maxSalePrice;
		this.minDiscountRate = builder.minDiscountRate;
		this.maxDiscountRate = builder.maxDiscountRate;
		this.sort = builder.sort;
		this.productSortField = builder.productSortField;
		this.cursorId = builder.cursorId;
		this.productSearchField = builder.productSearchField;
		this.searchWord = builder.searchWord;
		this.createdAtFrom = builder.createdAtFrom;
		this.createdAtTo = builder.createdAtTo;
		this.updatedAtFrom = builder.updatedAtFrom;
		this.updatedAtTo = builder.updatedAtTo;
		this.simpleQuery = builder.simpleQuery;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private int page = 0;
		private int size = 20;
		private List<Long> sellerIds = List.of();
		private List<Long> productGroupIds = List.of();
		private List<Long> categoryIds = List.of();
		private List<Long> brandIds = List.of();
		private ManagementType managementType;
		private Boolean displayed;
		private Boolean soldOut;
		private Long minSalePrice;
		private Long maxSalePrice;
		private Long minDiscountRate;
		private Long maxDiscountRate;
		private Sort sort = Sort.DESC;
		private ProductSortField productSortField = ProductSortField.ID;
		private Long cursorId;
		private ProductSearchField productSearchField;
		private String searchWord;
		private LocalDateTime createdAtFrom;
		private LocalDateTime createdAtTo;
		private LocalDateTime updatedAtFrom;
		private LocalDateTime updatedAtTo;
		private boolean simpleQuery = false;

		public Builder page(int page) {
			this.page = page;
			return this;
		}

		public Builder size(int size) {
			this.size = size;
			return this;
		}

		public Builder sellerIds(List<Long> sellerIds) {
			this.sellerIds = sellerIds;
			return this;
		}

		public Builder productGroupIds(List<Long> productGroupIds) {
			this.productGroupIds = productGroupIds;
			return this;
		}

		public Builder categoryIds(List<Long> categoryIds) {
			this.categoryIds = categoryIds;
			return this;
		}

		public Builder brandIds(List<Long> brandIds) {
			this.brandIds = brandIds;
			return this;
		}

		public Builder managementType(ManagementType managementType) {
			this.managementType = managementType;
			return this;
		}

		public Builder displayed(Boolean displayed) {
			this.displayed = displayed;
			return this;
		}

		public Builder soldOut(Boolean soldOut) {
			this.soldOut = soldOut;
			return this;
		}

		public Builder minSalePrice(Long minSalePrice) {
			this.minSalePrice = minSalePrice;
			return this;
		}

		public Builder maxSalePrice(Long maxSalePrice) {
			this.maxSalePrice = maxSalePrice;
			return this;
		}

		public Builder minDiscountRate(Long minDiscountRate) {
			this.minDiscountRate = minDiscountRate;
			return this;
		}

		public Builder maxDiscountRate(Long maxDiscountRate) {
			this.maxDiscountRate = maxDiscountRate;
			return this;
		}

		public Builder sort(Sort sort) {
			this.sort = sort;
			return this;
		}

		public Builder productSortField(ProductSortField productSortField) {
			this.productSortField = productSortField;
			return this;
		}

		public Builder cursorId(Long cursorId) {
			this.cursorId = cursorId;
			return this;
		}

		public Builder productSearchField(ProductSearchField productSearchField) {
			this.productSearchField = productSearchField;
			return this;
		}

		public Builder searchWord(String searchWord) {
			this.searchWord = searchWord;
			return this;
		}

		public Builder createdAtFrom(LocalDateTime createdAtFrom) {
			this.createdAtFrom = createdAtFrom;
			return this;
		}

		public Builder createdAtTo(LocalDateTime createdAtTo) {
			this.createdAtTo = createdAtTo;
			return this;
		}

		public Builder updatedAtFrom(LocalDateTime updatedAtFrom) {
			this.updatedAtFrom = updatedAtFrom;
			return this;
		}

		public Builder updatedAtTo(LocalDateTime updatedAtTo) {
			this.updatedAtTo = updatedAtTo;
			return this;
		}

		public Builder simpleQuery(boolean simpleQuery) {
			this.simpleQuery = simpleQuery;
			return this;
		}

		public ProductGroupSearchCondition build() {
			return new ProductGroupSearchCondition(this);
		}
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public List<Long> getSellerIds() {
		return sellerIds;
	}

	public List<Long> getProductGroupIds() {
		return productGroupIds;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public List<Long> getBrandIds() {
		return brandIds;
	}

	public ManagementType getManagementType() {
		return managementType;
	}

	public Boolean getDisplayed() {
		return displayed;
	}

	public Boolean getSoldOut() {
		return soldOut;
	}

	public Long getMinSalePrice() {
		return minSalePrice;
	}

	public Long getMaxSalePrice() {
		return maxSalePrice;
	}

	public Long getMinDiscountRate() {
		return minDiscountRate;
	}

	public Long getMaxDiscountRate() {
		return maxDiscountRate;
	}

	public Sort getSort() {
		return sort;
	}

	public ProductSortField getProductSortField() {
		return productSortField;
	}

	public Long getCursorId() {
		return cursorId;
	}

	public ProductSearchField getProductSearchField() {
		return productSearchField;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public LocalDateTime getCreatedAtFrom() {
		return createdAtFrom;
	}

	public LocalDateTime getCreatedAtTo() {
		return createdAtTo;
	}

	public LocalDateTime getUpdatedAtFrom() {
		return updatedAtFrom;
	}

	public LocalDateTime getUpdatedAtTo() {
		return updatedAtTo;
	}

	public boolean isSimpleQuery() {
		return simpleQuery;
	}
}
