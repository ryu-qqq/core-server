package com.ryuqq.core.domain.external;

import java.math.BigDecimal;
import java.util.Objects;

import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.enums.SyncStatus;

public class ExternalProductGroup {
	private final long siteId;
	private final SiteName siteName;
	private final long sellerId;
	private final String sellerName;
	private final long productGroupId;
	private final String externalProductGroupId;
	private final long brandId;
	private final String externalBrandId;
	private final long categoryId;
	private final String externalCategoryId;
	private final String productName;
	private final BigDecimal regularPrice;
	private final BigDecimal currentPrice;
	private final SyncStatus status;
	private final boolean fixedPrice;
	private final boolean soldOut;
	private final boolean displayed;

	private ExternalProductGroup(long siteId, SiteName siteName, String sellerName, long productGroupId, String externalProductGroupId,
								 long brandId, String externalBrandId, long categoryId, String externalCategoryId, long sellerId,
								 String productName, BigDecimal regularPrice, BigDecimal currentPrice, SyncStatus status,
								 boolean fixedPrice, boolean soldOut, boolean displayed) {
		this.siteId = siteId;
		this.siteName = siteName;
		this.sellerName = sellerName;
		this.productGroupId = productGroupId;
		this.externalProductGroupId = externalProductGroupId;
		this.brandId = brandId;
		this.externalBrandId = externalBrandId;
		this.categoryId = categoryId;
		this.externalCategoryId = externalCategoryId;
		this.sellerId = sellerId;
		this.productName = productName;
		this.regularPrice = regularPrice;
		this.currentPrice = currentPrice;
		this.status = status;
		this.fixedPrice = fixedPrice;
		this.soldOut = soldOut;
		this.displayed = displayed;
	}

	public static ExternalProductGroup create(long siteId, SiteName siteName, long productGroupId, long brandId, long categoryId,  long sellerId, SyncStatus status) {
		return new ExternalProductGroup(siteId, siteName, null, productGroupId, null, brandId, null,
			categoryId, null, sellerId, null, BigDecimal.ZERO, BigDecimal.ZERO, status, false, false, true);
	}

	public static ExternalProductGroup create(long siteId, SiteName siteName, String sellerName, long productGroupId, String externalProductGroupId,
											  long brandId, String externalBrandId, long categoryId, String externalCategoryId,  long sellerId,
											  String productName, BigDecimal regularPrice, BigDecimal currentPrice, SyncStatus status,
											  boolean fixedPrice, boolean soldOut, boolean displayed) {
		return new ExternalProductGroup(siteId, siteName, sellerName, productGroupId, externalProductGroupId, brandId, externalBrandId,
			categoryId, externalCategoryId, sellerId, productName, regularPrice, currentPrice, status, fixedPrice, soldOut, displayed);
	}

	public long getSiteId() {
		return siteId;
	}

	public SiteName getSiteName() {
		return siteName;
	}

	public long getProductGroupId() {
		return productGroupId;
	}

	public String getExternalProductGroupId() {
		return externalProductGroupId;
	}

	public long getBrandId() {
		return brandId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public String getExternalBrandId() {
		return externalBrandId;
	}

	public String getExternalCategoryId() {
		if(externalCategoryId == null) {
			return "";
		}
		return externalCategoryId;
	}

	public long getSellerId() {
		return sellerId;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getRegularPrice() {
		return regularPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public SyncStatus getStatus() {
		return status;
	}

	public boolean isFixedPrice() {
		return fixedPrice;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public String getSellerName() {
		return sellerName;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		ExternalProductGroup that = (ExternalProductGroup) object;
		return siteId == that.siteId &&
			productGroupId == that.productGroupId &&
			brandId == that.brandId &&
			categoryId == that.categoryId &&
			fixedPrice == that.fixedPrice &&
			soldOut == that.soldOut &&
			displayed == that.displayed &&
			siteName == that.siteName &&
			Objects.equals(externalProductGroupId, that.externalProductGroupId) &&
			Objects.equals(externalBrandId, that.externalBrandId) &&
			Objects.equals(externalCategoryId, that.externalCategoryId) &&
			Objects.equals(productName, that.productName) &&
			Objects.equals(regularPrice, that.regularPrice) &&
			Objects.equals(currentPrice, that.currentPrice) &&
			status == that.status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(siteId, siteName, sellerId, sellerName, productGroupId, externalProductGroupId, brandId,
			externalBrandId, categoryId, externalCategoryId, productName, regularPrice, currentPrice, status,
			fixedPrice,
			soldOut, displayed);
	}

	@Override
	public String toString() {
		return "ExternalProductGroup{"
			+
			"siteId="
			+ siteId
			+
			", siteName="
			+ siteName
			+
			", sellerId="
			+ sellerId
			+
			", sellerName='"
			+ sellerName
			+ '\''
			+
			", productGroupId="
			+ productGroupId
			+
			", externalProductGroupId='"
			+ externalProductGroupId
			+ '\''
			+
			", brandId="
			+ brandId
			+
			", externalBrandId='"
			+ externalBrandId
			+ '\''
			+
			", categoryId="
			+ categoryId
			+
			", externalCategoryId='"
			+ externalCategoryId
			+ '\''
			+
			", productName='"
			+ productName
			+ '\''
			+
			", regularPrice="
			+ regularPrice
			+
			", currentPrice="
			+ currentPrice
			+
			", status="
			+ status
			+
			", fixedPrice="
			+ fixedPrice
			+
			", soldOut="
			+ soldOut
			+
			", displayed="
			+ displayed
			+
			'}';
	}

	public static class Builder {
		private long siteId;
		private SiteName siteName;
		private String sellerName;
		private long productGroupId;
		private String externalProductGroupId;
		private long brandId;
		private String externalBrandId;
		private long categoryId;
		private String externalCategoryId;
		private long sellerId;
		private String productName;
		private BigDecimal regularPrice;
		private BigDecimal currentPrice;
		private SyncStatus status;
		private boolean fixedPrice;
		private boolean soldOut;
		private boolean displayed;

		public Builder() {}

		public Builder(ExternalProductGroup existingGroup) {
			this.siteId = existingGroup.getSiteId();
			this.siteName = existingGroup.getSiteName();
			this.sellerName = existingGroup.getSellerName();
			this.productGroupId = existingGroup.getProductGroupId();
			this.externalProductGroupId = existingGroup.getExternalProductGroupId();
			this.brandId = existingGroup.getBrandId();
			this.externalBrandId = existingGroup.getExternalBrandId();
			this.categoryId = existingGroup.getCategoryId();
			this.externalCategoryId = existingGroup.getExternalCategoryId();
			this.sellerId = existingGroup.getSellerId();
			this.productName = existingGroup.getProductName();
			this.regularPrice = existingGroup.getRegularPrice();
			this.currentPrice = existingGroup.getCurrentPrice();
			this.status = existingGroup.getStatus();
			this.fixedPrice = existingGroup.isFixedPrice();
			this.soldOut = existingGroup.isSoldOut();
			this.displayed = existingGroup.isDisplayed();
		}

		public Builder siteId(long siteId) {
			this.siteId = siteId;
			return this;
		}

		public Builder siteName(SiteName siteName) {
			this.siteName = siteName;
			return this;
		}

		public Builder productGroupId(long productGroupId) {
			this.productGroupId = productGroupId;
			return this;
		}

		public Builder externalProductGroupId(String externalProductGroupId) {
			this.externalProductGroupId = externalProductGroupId;
			return this;
		}

		public Builder brandId(long brandId) {
			this.brandId = brandId;
			return this;
		}

		public Builder externalBrandId(String externalBrandId) {
			this.externalBrandId = externalBrandId;
			return this;
		}

		public Builder categoryId(long categoryId) {
			this.categoryId = categoryId;
			return this;
		}

		public Builder externalCategoryId(String externalCategoryId) {
			this.externalCategoryId = externalCategoryId;
			return this;
		}

		public Builder sellerId(long sellerId) {
			this.sellerId = sellerId;
			return this;
		}

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder regularPrice(BigDecimal regularPrice) {
			this.regularPrice = regularPrice;
			return this;
		}

		public Builder currentPrice(BigDecimal currentPrice) {
			this.currentPrice = currentPrice;
			return this;
		}

		public Builder status(SyncStatus status) {
			this.status = status;
			return this;
		}

		public Builder fixedPrice(boolean fixedPrice) {
			this.fixedPrice = fixedPrice;
			return this;
		}

		public Builder soldOut(boolean soldOut) {
			this.soldOut = soldOut;
			return this;
		}

		public Builder displayed(boolean displayed) {
			this.displayed = displayed;
			return this;
		}

		public Builder sellerName(String sellerName) {
			this.sellerName = sellerName;
			return this;
		}

		public ExternalProductGroup build() {
			return new ExternalProductGroup(siteId, siteName, sellerName, productGroupId, externalProductGroupId, brandId, externalBrandId,
				categoryId, externalCategoryId, sellerId, productName, regularPrice, currentPrice, status, fixedPrice, soldOut, displayed);
		}
	}
}
