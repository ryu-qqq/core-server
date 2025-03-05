package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.domain.product.ProductGroupImageContext;

public class DefaultProductGroupContext implements ProductGroupContext {

	private final ProductGroup productGroup;
	private final ProductDelivery productDelivery;
	private final ProductNotice productNotice;
	private final ProductDetailDescription productDetailDescription;
	private final ProductGroupImageContext productGroupImageContext;
	private final ProductOptionContext productOptionContext;

	private DefaultProductGroupContext(Builder builder) {
		this.productGroup = builder.productGroup;
		this.productDelivery = builder.productDelivery;
		this.productNotice = builder.productNotice;
		this.productDetailDescription = builder.productDetailDescription;
		this.productGroupImageContext = builder.productGroupImages;
		this.productOptionContext = builder.productOptionContext;
	}

	public static Builder builder() {
		return new Builder();
	}


	public static class Builder {
		private ProductGroup productGroup;
		private ProductDelivery productDelivery;
		private ProductNotice productNotice;
		private ProductDetailDescription productDetailDescription;
		private ProductGroupImageContext productGroupImages;
		private ProductOptionContext productOptionContext;

		public Builder productGroup(ProductGroup productGroup) {
			this.productGroup = productGroup;
			return this;
		}

		public Builder productDelivery(ProductDelivery productDelivery) {
			this.productDelivery = productDelivery;
			return this;
		}

		public Builder productNotice(ProductNotice productNotice) {
			this.productNotice = productNotice;
			return this;
		}

		public Builder productDetailDescription(ProductDetailDescription productDetailDescription) {
			this.productDetailDescription = productDetailDescription;
			return this;
		}

		public Builder productGroupImages(ProductGroupImageContext productGroupImages) {
			this.productGroupImages = productGroupImages;
			return this;
		}

		public Builder products(ProductOptionContext productOptionContext) {
			this.productOptionContext = productOptionContext;
			return this;
		}

		public DefaultProductGroupContext build() {
			return new DefaultProductGroupContext(this);
		}
	}

	@Override
	public long getId() {
		return productGroup.getId();
	}

	@Override
	public ProductGroup getProductGroup() {
		return productGroup;
	}

	@Override
	public ProductDelivery getProductDelivery() {
		return productDelivery;
	}

	@Override
	public ProductNotice getProductNotice() {
		return productNotice;
	}

	@Override
	public ProductDetailDescription getProductDetailDescription() {
		return productDetailDescription;
	}

	@Override
	public ProductGroupImageContext getProductGroupImageContext() {
		return productGroupImageContext;
	}

	@Override
	public ProductOptionContext getProductOptionContext() {
		return productOptionContext;
	}

	public long getSellerId() {
		return productGroup.getSellerId();
	}

	public long getProductGroupId() {
		return productGroup.getId();
	}

	public long getCategoryId(){return productGroup.getCategoryId();}

	public long getBrandId(){
		return productGroup.getBrandId();
	}

}
