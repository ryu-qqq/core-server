package com.ryuqq.core.domain.product;

import java.util.List;

import com.ryuqq.core.domain.product.core.Item;
import com.ryuqq.core.domain.product.core.ItemContext;
import com.ryuqq.core.domain.product.core.ItemDeliveryInfo;
import com.ryuqq.core.domain.product.core.ItemImage;
import com.ryuqq.core.domain.product.core.ItemNoticeInfo;
import com.ryuqq.core.domain.product.core.ItemRefundInfo;

public class ProductGroupContext implements ItemContext {

	private final ProductGroup productGroup;
	private final ProductDelivery productDelivery;
	private final ProductNotice productNotice;
	private final ProductDetailDescription productDetailDescription;
	private final ProductGroupImageBundle productGroupImages;
	private final ProductContextBundle products;

	private ProductGroupContext(Builder builder) {
		this.productGroup = builder.productGroup;
		this.productDelivery = builder.productDelivery;
		this.productNotice = builder.productNotice;
		this.productDetailDescription = builder.productDetailDescription;
		this.productGroupImages = builder.productGroupImages;
		this.products = builder.products;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public Item getItem() {
		return productGroup;
	}

	@Override
	public ItemNoticeInfo getNoticeInfo() {
		return productNotice;
	}

	@Override
	public ItemDeliveryInfo getDeliveryInfo() {
		return productDelivery;
	}

	@Override
	public ItemRefundInfo getRefundInfo() {
		return productDelivery;
	}

	@Override
	public List<? extends ItemImage> getItemImages() {
		return productGroupImages.getImages();
	}

	@Override
	public String getItemDescription() {
		return productDetailDescription.getDetailDescription();
	}

	public static class Builder {
		private ProductGroup productGroup;
		private ProductDelivery productDelivery;
		private ProductNotice productNotice;
		private ProductDetailDescription productDetailDescription;
		private ProductGroupImageBundle productGroupImages;
		private ProductContextBundle products;

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

		public Builder productGroupImages(ProductGroupImageBundle productGroupImages) {
			this.productGroupImages = productGroupImages;
			return this;
		}

		public Builder products(ProductContextBundle products) {
			this.products = products;
			return this;
		}

		public ProductGroupContext build() {
			return new ProductGroupContext(this);
		}
	}


	public ProductGroupContext assignProductGroupId(Long productGroupId) {
		return builder()
			.productGroup(this.productGroup.assignId(productGroupId))
			.productDelivery(this.productDelivery.assignProductGroupId(productGroupId))
			.productNotice(this.productNotice.assignProductGroupId(productGroupId))
			.productDetailDescription(this.productDetailDescription.assignProductGroupId(productGroupId))
			.productGroupImages(this.productGroupImages.assignProductGroupId(productGroupId))
			.products(this.products.assignProductGroupId(productGroupId))
			.build();
	}

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public ProductDelivery getProductDelivery() {
		return productDelivery;
	}

	public ProductNotice getProductNotice() {
		return productNotice;
	}

	public ProductDetailDescription getProductDetailDescription() {
		return productDetailDescription;
	}

	public ProductGroupImageBundle getProductGroupImages() {
		return productGroupImages;
	}

	public ProductContextBundle getProducts() {
		return products;
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
