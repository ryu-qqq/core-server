package com.ryuqq.core.api.controller.v1.product.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.controller.v1.product.request.ProductGroupImageRequestDto;
import com.ryuqq.core.enums.ProductImageType;

@Component
public class ProductImageValidator implements Validator<ProductGroupContextCommandRequestDto> {

	@Override
	public void validate(ProductGroupContextCommandRequestDto requestDto, ValidationResult result, boolean updated) {
		List<ProductGroupImageRequestDto> images = requestDto.productImageList();


		long mainImageCount = images.stream()
			.map(ProductGroupImageRequestDto::productImageType)
			.filter(type -> type == ProductImageType.MAIN)
			.count();

		if (mainImageCount == 0) {
			result.addError("At least one MAIN image is required.");
		}

		if (mainImageCount > 1) {
			result.addError("Only one MAIN image is allowed.");
		}

	}
}
