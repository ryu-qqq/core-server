package com.ryuqq.core.external.oco.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoProductInsertRequestDto(
	@JsonProperty("preAddYn") String preAddYn,
	@JsonProperty("pgid") int pgid,
	@JsonProperty("pcid") int pcid,
	@JsonProperty("product_type") String productType,
	@JsonProperty("name") String name,
	@JsonProperty("madein") String madeIn,
	@JsonProperty("manufacture") String manufacture,
	@JsonProperty("info_material") String infoMaterial,
	@JsonProperty("info_color") String infoColor,
	@JsonProperty("info_as_tel") String infoAsTel,
	@JsonProperty("info_qa_tel") String infoQaTel,
	@JsonProperty("info_addr") String infoAddr,
	@JsonProperty("code") String code,
	@JsonProperty("originprice") int originPrice,
	@JsonProperty("price") int price,
	@JsonProperty("sale_price_yn") String salePriceYn,
	@JsonProperty("sale_time_yn") String saleTimeYn,
	@JsonProperty("stock") int stock,
	@JsonProperty("keyword") String keyword,
	@JsonProperty("soldout") int soldOut,
	@JsonProperty("find_yn") String findYn,
	@JsonProperty("use_coupon_yn") String useCouponYn,
	@JsonProperty("hidden") int hidden,
	@JsonProperty("product_naver_yn") String productNaverYn,
	@JsonProperty("option_yn") String optionYn,
	@JsonProperty("option_input_yn") String optionInputYn,
	@JsonProperty("option_count") int optionCount,
	@JsonProperty("deliveryprice_free_yn") String deliveryPriceFreeYn,
	@JsonProperty("re_delivery_view_yn") String reDeliveryViewYn,
	@JsonProperty("list_image_file") String listImageFile,
	@JsonProperty("fileList") List<OcoImageInsertRequestDto> fileList,
	@JsonProperty("main_image_path") String mainImagePath,
	@JsonProperty("content") String content,
	@JsonProperty("optionList") List<OcoOptionInsertRequestDto> optionList,
	@JsonInclude(JsonInclude.Include.NON_NULL) @JsonProperty("pid") Integer pid,
	@JsonProperty("sale_price") Integer salePrice,
	@JsonProperty("sale_start_day") String saleStartDay,
	@JsonProperty("sale_end_day") String saleEndDay,
	@JsonProperty("sale_time_start_date") String saleTimeStartDate,
	@JsonProperty("sale_time_end_date") String saleTimeEndDate
) {
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String preAddYn;
		private int pgid;
		private int pcid;
		private String productType;
		private String name;
		private String madeIn;
		private String manufacture;
		private String infoMaterial;
		private String infoColor;
		private String infoAsTel;
		private String infoQaTel;
		private String infoAddr;
		private String code;
		private int originPrice;
		private int price;
		private String salePriceYn;
		private String saleTimeYn;
		private int stock;
		private String keyword;
		private int soldOut;
		private String findYn;
		private String useCouponYn;
		private int hidden;
		private String productNaverYn;
		private String optionYn;
		private String optionInputYn;
		private int optionCount;
		private String deliveryPriceFreeYn;
		private String reDeliveryViewYn;
		private String listImageFile;
		private List<OcoImageInsertRequestDto> fileList;
		private String mainImagePath;
		private String content;
		private List<OcoOptionInsertRequestDto> optionList;
		private Integer pid;
		private Integer salePrice;
		private String saleStartDay;
		private String saleEndDay;
		private String saleTimeStartDate;
		private String saleTimeEndDate;

		public Builder preAddYn(String preAddYn) {
			this.preAddYn = preAddYn;
			return this;
		}

		public Builder pgid(int pgid) {
			this.pgid = pgid;
			return this;
		}

		public Builder pcid(int pcid) {
			this.pcid = pcid;
			return this;
		}

		public Builder productType(String productType) {
			this.productType = productType;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder madeIn(String madeIn) {
			this.madeIn = madeIn;
			return this;
		}

		public Builder manufacture(String manufacture) {
			this.manufacture = manufacture;
			return this;
		}

		public Builder infoMaterial(String infoMaterial) {
			this.infoMaterial = infoMaterial;
			return this;
		}

		public Builder infoColor(String infoColor) {
			this.infoColor = infoColor;
			return this;
		}

		public Builder infoAsTel(String infoAsTel) {
			this.infoAsTel = infoAsTel;
			return this;
		}

		public Builder infoQaTel(String infoQaTel) {
			this.infoQaTel = infoQaTel;
			return this;
		}

		public Builder infoAddr(String infoAddr) {
			this.infoAddr = infoAddr;
			return this;
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder originPrice(int originPrice) {
			this.originPrice = originPrice;
			return this;
		}

		public Builder price(int price) {
			this.price = price;
			return this;
		}

		public Builder salePriceYn(String salePriceYn) {
			this.salePriceYn = salePriceYn;
			return this;
		}

		public Builder saleTimeYn(String saleTimeYn) {
			this.saleTimeYn = saleTimeYn;
			return this;
		}

		public Builder stock(int stock) {
			this.stock = stock;
			return this;
		}

		public Builder keyword(String keyword) {
			this.keyword = keyword;
			return this;
		}

		public Builder soldOut(int soldOut) {
			this.soldOut = soldOut;
			return this;
		}

		public Builder findYn(String findYn) {
			this.findYn = findYn;
			return this;
		}

		public Builder useCouponYn(String useCouponYn) {
			this.useCouponYn = useCouponYn;
			return this;
		}

		public Builder hidden(int hidden) {
			this.hidden = hidden;
			return this;
		}

		public Builder productNaverYn(String productNaverYn) {
			this.productNaverYn = productNaverYn;
			return this;
		}

		public Builder optionYn(String optionYn) {
			this.optionYn = optionYn;
			return this;
		}

		public Builder optionInputYn(String optionInputYn) {
			this.optionInputYn = optionInputYn;
			return this;
		}

		public Builder optionCount(int optionCount) {
			this.optionCount = optionCount;
			return this;
		}

		public Builder deliveryPriceFreeYn(String deliveryPriceFreeYn) {
			this.deliveryPriceFreeYn = deliveryPriceFreeYn;
			return this;
		}

		public Builder reDeliveryViewYn(String reDeliveryViewYn) {
			this.reDeliveryViewYn = reDeliveryViewYn;
			return this;
		}

		public Builder listImageFile(String listImageFile) {
			this.listImageFile = listImageFile;
			return this;
		}

		public Builder fileList(List<OcoImageInsertRequestDto> fileList) {
			this.fileList = fileList;
			return this;
		}

		public Builder mainImagePath(String mainImagePath) {
			this.mainImagePath = mainImagePath;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Builder optionList(List<OcoOptionInsertRequestDto> optionList) {
			this.optionList = optionList;
			return this;
		}

		public Builder pid(Integer pid) {
			this.pid = pid;
			return this;
		}

		public Builder salePrice(Integer salePrice) {
			this.salePrice = salePrice;
			return this;
		}

		public Builder saleStartDay(String saleStartDay) {
			this.saleStartDay = saleStartDay;
			return this;
		}

		public Builder saleEndDay(String saleEndDay) {
			this.saleEndDay = saleEndDay;
			return this;
		}

		public Builder saleTimeStartDate(String saleTimeStartDate) {
			this.saleTimeStartDate = saleTimeStartDate;
			return this;
		}

		public Builder saleTimeEndDate(String saleTimeEndDate) {
			this.saleTimeEndDate = saleTimeEndDate;
			return this;
		}

		public OcoProductInsertRequestDto build() {
			return new OcoProductInsertRequestDto(preAddYn, pgid, pcid, productType, name, madeIn, manufacture, infoMaterial, infoColor, infoAsTel, infoQaTel, infoAddr, code, originPrice, price, salePriceYn, saleTimeYn, stock, keyword, soldOut, findYn, useCouponYn, hidden, productNaverYn, optionYn, optionInputYn, optionCount, deliveryPriceFreeYn, reDeliveryViewYn, listImageFile, fileList, mainImagePath, content, optionList, pid, salePrice, saleStartDay, saleEndDay, saleTimeStartDate, saleTimeEndDate);
		}
	}
}
