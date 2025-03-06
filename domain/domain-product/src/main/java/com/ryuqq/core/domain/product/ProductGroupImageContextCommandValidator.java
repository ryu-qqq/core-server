package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;
import com.ryuqq.core.enums.ProductImageType;

@Component
public class ProductGroupImageContextCommandValidator implements
	ProductGroupDomainValidator<ProductGroupImageContextCommand> {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductGroupImageContextCommand.class.isAssignableFrom(clazz) || clazz.equals(ProductGroupImageContextCommand.class);
	}

	@Override
	public void validate(ProductGroupImageContextCommand target, ValidationResult result, boolean updated) {
		long mainImageCount = getMainImageCount(target.productGroupImageCommands());

		if (mainImageCount == 0) {
			result.addError("At least one MAIN image is required.");
		}

		if (mainImageCount > 1) {
			result.addError("Only one MAIN image is allowed.");
		}
	}

	private long getMainImageCount(List<? extends ProductGroupImageCommand> productGroupImageCommands) {
		return productGroupImageCommands.stream()
			.map(ProductGroupImageCommand::productImageType)
			.filter(type -> type == ProductImageType.MAIN)
			.count();
	}

}
