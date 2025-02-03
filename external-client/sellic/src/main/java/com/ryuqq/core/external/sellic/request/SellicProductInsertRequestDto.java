package com.ryuqq.core.external.sellic.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.ryuqq.core.external.sellic.SellicOrigin;

public record SellicProductInsertRequestDto(
	@JsonProperty("product_name")
	String productGroupName,
	@JsonProperty("own_code")
 	String ownCode,
	@JsonProperty("origin")
 	int origin,
	@JsonProperty("supplier_name")
	String supplierName,
	@JsonProperty("category_id")
	int categoryId,
	@JsonProperty("sale_status")
	int saleStatus,
	@JsonProperty("delivery_charge_type")
	int deliveryChargeType,
	@JsonProperty("delivery_fee")
	String deliveryFee,
	@JsonProperty("tax")
	int tax,
	@JsonProperty("brand")
	String brand,
	@JsonProperty("detail_note")
	String detailNote,
	@JsonProperty("market_price")
	int regularPrice,
	@JsonProperty("sale_price")
	int currentPrice,
	@JsonProperty("image1")
	String image1,
	@JsonProperty("image2")
	String image2,
	@JsonProperty("image3")
	String image3,
	@JsonProperty("image4")
	String image4,
	@JsonProperty("image5")
	String image5,
	@JsonProperty("image6")
	String image6,
	@JsonProperty("image7")
	String image7,
	@JsonProperty("image8")
	String image8,
	@JsonProperty("image9")
	String image9,
	@JsonProperty("image10")
	String image10,
	@JsonProperty("image11")
	String image11,
	@JsonProperty("notify_code")
	String notifyCode,
	@JsonProperty("notify1")
	String notify1,
	@JsonProperty("notify2")
	String notify2,
	@JsonProperty("notify3")
	String notify3,
	@JsonProperty("notify4")
	String notify4,
	@JsonProperty("notify5")
	String notify5,
	@JsonProperty("notify6")
	String notify6,
	@JsonProperty("notify7")
	String notify7,
	@JsonProperty("notify8")
	String notify8,
	@JsonProperty("notify9")
	String notify9,
	@JsonProperty("option_name1")
	String optionName1,
	@JsonProperty("option_name2")
	String optionName2,
	@JsonProperty("product_stocks")
	List<SellicProductOptionInsertRequestDto> options,
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("product_id")
	Long productId
) {

	public static class Builder {
		private String productName;
		private String ownCode;
		private int origin;
		private String supplierName;
		private int categoryId;
		private int saleStatus;
		private int deliveryChargeType;
		private String deliveryFee;
		private int tax;
		private String brand;
		private String detailNote;
		private int marketPrice;
		private int salePrice;
		private String image1 = "";
		private String image2 = "";
		private String image3 = "";
		private String image4 = "";
		private String image5 = "";
		private String image6 = "";
		private String image7 = "";
		private String image8 = "";
		private String image9 = "";
		private String image10 = "";
		private String image11 = "";
		private String notifyCode = "";
		private String notify1 = "";
		private String notify2 = "";
		private String notify3 = "";
		private String notify4 = "";
		private String notify5 = "";
		private String notify6 = "";
		private String notify7 = "";
		private String notify8 = "";
		private String notify9 = "";
		private String optionName1;
		private String optionName2;
		private List<SellicProductOptionInsertRequestDto> options;
		private Long productId;

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder ownCode(String ownCode) {
			this.ownCode = ownCode;
			return this;
		}

		public Builder origin(SellicOrigin origin) {
			this.origin = origin.getCode();
			return this;
		}

		public Builder supplierName(String supplierName) {
			this.supplierName = supplierName;
			return this;
		}

		public Builder categoryId(int categoryId) {
			this.categoryId = categoryId;
			return this;
		}

		public Builder saleStatus(int saleStatus) {
			this.saleStatus = saleStatus;
			return this;
		}

		public Builder deliveryChargeType(int deliveryChargeType) {
			this.deliveryChargeType = deliveryChargeType;
			return this;
		}

		public Builder deliveryFee(String deliveryFee) {
			this.deliveryFee = deliveryFee;
			return this;
		}

		public Builder tax(int tax) {
			this.tax = tax;
			return this;
		}

		public Builder brand(String brand) {
			this.brand = brand;
			return this;
		}

		public Builder detailNote(String detailNote) {
			this.detailNote = detailNote;
			return this;
		}

		public Builder marketPrice(int marketPrice) {
			this.marketPrice = marketPrice;
			return this;
		}

		public Builder salePrice(int salePrice) {
			this.salePrice = salePrice;
			return this;
		}

		public Builder image1(String image1) {
			this.image1 = image1;
			return this;
		}

		public Builder image2(String image2) {
			this.image2 = image2;
			return this;
		}

		public Builder image3(String image3) {
			this.image3 = image3;
			return this;
		}

		public Builder image4(String image4) {
			this.image4 = image4;
			return this;
		}

		public Builder image5(String image5) {
			this.image5 = image5;
			return this;
		}

		public Builder image6(String image6) {
			this.image6 = image6;
			return this;
		}

		public Builder image7(String image7) {
			this.image7 = image7;
			return this;
		}

		public Builder image8(String image8) {
			this.image8 = image8;
			return this;
		}

		public Builder image9(String image9) {
			this.image9 = image9;
			return this;
		}

		public Builder image10(String image10) {
			this.image10 = image10;
			return this;
		}

		public Builder image11(String image11) {
			this.image11 = image11;
			return this;
		}

		public Builder notifyCode(String notifyCode) {
			this.notifyCode = notifyCode;
			return this;
		}

		public Builder notify1(String notify1) {
			this.notify1 = notify1;
			return this;
		}

		public Builder notify2(String notify2) {
			this.notify2 = notify2;
			return this;
		}

		public Builder notify3(String notify3) {
			this.notify3 = notify3;
			return this;
		}

		public Builder notify4(String notify4) {
			this.notify4 = notify4;
			return this;
		}

		public Builder notify5(String notify5) {
			this.notify5 = notify5;
			return this;
		}

		public Builder notify6(String notify6) {
			this.notify6 = notify6;
			return this;
		}

		public Builder notify7(String notify7) {
			this.notify7 = notify7;
			return this;
		}

		public Builder notify8(String notify8) {
			this.notify8 = notify8;
			return this;
		}

		public Builder notify9(String notify9) {
			this.notify9 = notify9;
			return this;
		}

		public Builder optionName1(String optionName1) {
			this.optionName1 = optionName1;
			return this;
		}

		public Builder optionName2(String optionName2) {
			this.optionName2 = optionName2;
			return this;
		}

		public Builder options(List<SellicProductOptionInsertRequestDto> options) {
			this.options = options;
			return this;
		}

		public Builder productId(Long productId) {
			this.productId = productId;
			return this;
		}

		public SellicProductInsertRequestDto build() {
			return new SellicProductInsertRequestDto(
				productName, ownCode, origin, supplierName, categoryId, saleStatus, deliveryChargeType,
				deliveryFee, tax, brand, detailNote, marketPrice, salePrice, image1, image2, image3,
				image4, image5, image6, image7, image8, image9, image10, image11, notifyCode, notify1,
				notify2, notify3, notify4, notify5, notify6, notify7, notify8, notify9, optionName1,
				optionName2, options, productId
			);
		}
	}











}
