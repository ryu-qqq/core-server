package com.ryuqq.core.external.buyma.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyMaProductInsertRequestDto {
	@JsonProperty("reference_number")
	private String referenceNumber;

	@JsonProperty("control")
	private String control;

	@JsonProperty("name")
	private String name;

	@JsonProperty("comments")
	private String comments;

	@JsonProperty("brand_id")
	private long brandId;

	@JsonProperty("brand_name")
	private String brandName;

	@JsonProperty("category_id")
	private long categoryId;

	@JsonProperty("price")
	private int price;

	@JsonProperty("reference_price")
	private int referencePrice;

	@JsonProperty("available_until")
	private String availableUntil;

	@JsonProperty("buying_area_id")
	private  int buyingAreaId;

	@JsonProperty("shipping_area_id")
	private int shippingAreaId;

	@JsonProperty("images")
	private List<BuyMaImageInsertRequestDto> images;

	@JsonProperty("shipping_methods")
	private List<BuyMaShippingMethodDto> shippingMethods;

	@JsonProperty("variants")
	private List<BuyMaVariantInsertRequestDto> variants;

	@JsonProperty("options")
	private List<BuyMaOptionInsertRequestDto> options;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("id")
	private Long id;

	@JsonProperty("duty")
	private String duty;

	@JsonProperty("colorsize_comments")
	private String colorSizeComment;


	public static class Builder {
		private String referenceNumber;
		private String control;
		private String name;
		private String comments;
		private Long brandId;
		private String brandName;
		private Long categoryId;
		private int price;
		private int referencePrice;
		private String availableUntil;
		private int buyingAreaId;
		private int shippingAreaId;
		private List<BuyMaImageInsertRequestDto> images;
		private List<BuyMaShippingMethodDto> shippingMethods;
		private List<BuyMaVariantInsertRequestDto> variants;
		private List<BuyMaOptionInsertRequestDto> options;
		private Long id;
		private String duty;
		private String colorSizeComment;

		public Builder referenceNumber(String referenceNumber) {
			this.referenceNumber = referenceNumber;
			return this;
		}

		public Builder control(String control) {
			this.control = control;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder comments(String comments) {
			this.comments = comments;
			return this;
		}

		public Builder brandId(Long brandId) {
			this.brandId = brandId;
			return this;
		}

		public Builder brandName(String brandName) {
			this.brandName = brandName;
			return this;
		}

		public Builder categoryId(Long categoryId) {
			this.categoryId = categoryId;
			return this;
		}

		public Builder price(int price) {
			this.price = price;
			return this;
		}

		public Builder referencePrice(int referencePrice) {
			this.referencePrice = referencePrice;
			return this;
		}

		public Builder availableUntil(String availableUntil) {
			this.availableUntil = availableUntil;
			return this;
		}

		public Builder buyingAreaId(int buyingAreaId) {
			this.buyingAreaId = buyingAreaId;
			return this;
		}

		public Builder shippingAreaId(int shippingAreaId) {
			this.shippingAreaId = shippingAreaId;
			return this;
		}

		public Builder images(List<BuyMaImageInsertRequestDto> images) {
			this.images = images;
			return this;
		}

		public Builder shippingMethods(List<BuyMaShippingMethodDto> shippingMethods) {
			this.shippingMethods = shippingMethods;
			return this;
		}

		public Builder variants(List<BuyMaVariantInsertRequestDto> variants) {
			this.variants = variants;
			return this;
		}

		public Builder options(List<BuyMaOptionInsertRequestDto> options) {
			this.options = options;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder duty(String duty) {
			this.duty = duty;
			return this;
		}

		public Builder colorSizeComment(String colorSizeComment) {
			this.colorSizeComment = colorSizeComment;
			return this;
		}

		public BuyMaProductInsertRequestDto build() {
			return new BuyMaProductInsertRequestDto(this);
		}
	}

	private BuyMaProductInsertRequestDto(Builder builder) {
		this.referenceNumber = builder.referenceNumber;
		this.control = builder.control;
		this.name = builder.name;
		this.comments = builder.comments;
		this.brandId = builder.brandId;
		this.brandName = builder.brandName;
		this.categoryId = builder.categoryId;
		this.price = builder.price;
		this.referencePrice = builder.referencePrice;
		this.availableUntil = builder.availableUntil;
		this.buyingAreaId = builder.buyingAreaId;
		this.shippingAreaId = builder.shippingAreaId;
		this.images = builder.images;
		this.shippingMethods = builder.shippingMethods;
		this.variants = builder.variants;
		this.options = builder.options;
		this.id = builder.id;
		this.duty = builder.duty;
		this.colorSizeComment = builder.colorSizeComment;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getReferencePrice() {
		return referencePrice;
	}
}
