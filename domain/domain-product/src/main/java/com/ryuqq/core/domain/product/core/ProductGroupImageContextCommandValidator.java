package com.ryuqq.core.domain.product.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ProductImageType;

@Component
public class ProductGroupImageContextCommandValidator implements ProductGroupDomainValidator<ProductGroupImageContextCommand> {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductGroupImageContextCommand.class.isAssignableFrom(clazz) || clazz.equals(ProductGroupImageContextCommand.class);
	}

	@Override
	public void validate(ProductGroupImageContextCommand target, ValidationResult result, boolean updated) {
		long mainImageCount = target.productGroupImageCommands().stream()
			.map(ProductGroupImageCommand::productImageType)
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
