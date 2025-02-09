package com.ryuqq.core.api.controller.v1.product.request;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record ProductInsertRequestDto(

	@JsonInclude(JsonInclude.Include.NON_NULL)
	Long productId,
	boolean soldOut,
	boolean displayed,

	@NotNull(message = "Quantity cannot be null.")
	@Max(value = 999, message = "Quantity must be 999 or less.")
	int quantity,

	@NotNull(message = "Additional Price cannot be null.")
	@DecimalMax(value = "100000000", message = "Additional Price must be less than or equal to 100,000,000.")
	BigDecimal additionalPrice,

	@Valid
	List<ProductOptionInsertRequestDto> options
) {}
