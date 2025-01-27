package com.ryuqq.core.api.controller.v1.product.request;

import com.ryuqq.core.enums.ProductImageType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductGroupImageRequestDto(
	@NotNull(message = "ProductImage Type cannot be null.")
	ProductImageType productImageType,

	@NotBlank(message = "Image Url Name cannot be blank.")
	@Size(max = 255, message = "Image Url must be 255 characters or less.")
	String imageUrl
) {
}
