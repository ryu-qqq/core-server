package com.ryuqq.core.domain.product;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductOptionContext;

public class DefaultProductGroupContext implements ProductGroupContext {

	private final DefaultProductGroup defaultProductGroup;
	private final DefaultProductDelivery defaultProductDelivery;
	private final DefaultProductNotice defaultProductNotice;
	private final DefaultProductDetailDescription defaultProductDetailDescription;
	private final DefaultProductGroupImageContext defaultProductGroupImageContext;
	private final DefaultProductOptionContext defaultProductOptionContext;

	private DefaultProductGroupContext(Builder builder) {
		this.defaultProductGroup = builder.defaultProductGroup;
		this.defaultProductDelivery = builder.defaultProductDelivery;
		this.defaultProductNotice = builder.defaultProductNotice;
		this.defaultProductDetailDescription = builder.defaultProductDetailDescription;
		this.defaultProductGroupImageContext = builder.productGroupImages;
		this.defaultProductOptionContext = builder.products;
	}

	public static Builder builder() {
		return new Builder();
	}


	public static class Builder {
		private DefaultProductGroup defaultProductGroup;
		private DefaultProductDelivery defaultProductDelivery;
		private DefaultProductNotice defaultProductNotice;
		private DefaultProductDetailDescription defaultProductDetailDescription;
		private DefaultProductGroupImageContext productGroupImages;
		private DefaultProductOptionContext products;

		public Builder productGroup(DefaultProductGroup defaultProductGroup) {
			this.defaultProductGroup = defaultProductGroup;
			return this;
		}

		public Builder productDelivery(DefaultProductDelivery defaultProductDelivery) {
			this.defaultProductDelivery = defaultProductDelivery;
			return this;
		}

		public Builder productNotice(DefaultProductNotice defaultProductNotice) {
			this.defaultProductNotice = defaultProductNotice;
			return this;
		}

		public Builder productDetailDescription(DefaultProductDetailDescription defaultProductDetailDescription) {
			this.defaultProductDetailDescription = defaultProductDetailDescription;
			return this;
		}

		public Builder productGroupImages(DefaultProductGroupImageContext productGroupImages) {
			this.productGroupImages = productGroupImages;
			return this;
		}

		public Builder products(DefaultProductOptionContext products) {
			this.products = products;
			return this;
		}

		public DefaultProductGroupContext build() {
			return new DefaultProductGroupContext(this);
		}
	}


	public DefaultProductGroup getProductGroup() {
		return defaultProductGroup;
	}

	public DefaultProductDelivery getProductDelivery() {
		return defaultProductDelivery;
	}

	public DefaultProductNotice getProductNotice() {
		return defaultProductNotice;
	}

	public DefaultProductDetailDescription getProductDetailDescription() {
		return defaultProductDetailDescription;
	}

	public DefaultProductGroupImageContext getProductGroupImageContext() {
		return defaultProductGroupImageContext;
	}

	@Override
	public ProductOptionContext getProductOptionContext() {
		return defaultProductOptionContext;
	}

	public long getSellerId() {
		return defaultProductGroup.getSellerId();
	}

	public long getProductGroupId() {
		return defaultProductGroup.getId();
	}

	public long getCategoryId(){return defaultProductGroup.getCategoryId();}

	public long getBrandId(){
		return defaultProductGroup.getBrandId();
	}

}
