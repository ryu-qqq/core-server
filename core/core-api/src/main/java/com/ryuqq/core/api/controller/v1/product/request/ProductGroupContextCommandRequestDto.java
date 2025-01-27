package com.ryuqq.core.api.controller.v1.product.request;

import java.util.List;

import com.ryuqq.core.enums.OptionType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductGroupContextCommandRequestDto(
	@NotNull(message = "Product Group cannot be null.")
	@Valid
	ProductGroupInsertRequestDto productGroup,

	@NotNull(message = "Product Notice cannot be null.")
	@Valid
	ProductNoticeInsertRequestDto productNotice,

	@NotNull(message = "Product Delivery cannot be null.")
	@Valid
	ProductDeliveryRequestDto productDelivery,

	@NotNull(message = "Product Image List cannot be null.")
	@Size(min = 1, max = 10, message = "Product Image List cannot be empty.")
	@Valid
	List<ProductGroupImageRequestDto> productImageList,

	@NotNull(message = "Product Detail Description cannot be null.")
	@Valid
	ProductDetailDescriptionRequestDto detailDescription,

	@Valid
	List<ProductInsertRequestDto> productOptions
) {

	public long getBrandId(){
		return productGroup.brandId();
	}

	public long getCategoryId(){
		return productGroup.categoryId();
	}

	public OptionType getOptionType(){
		return productGroup.optionType();
	}


}
