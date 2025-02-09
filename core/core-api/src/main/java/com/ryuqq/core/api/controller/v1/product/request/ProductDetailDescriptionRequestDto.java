package com.ryuqq.core.api.controller.v1.product.request;

import jakarta.validation.constraints.NotBlank;

public record ProductDetailDescriptionRequestDto(
	@NotBlank(message = "Detail Description cannot be blank.")
	String detailDescription
) {
}
