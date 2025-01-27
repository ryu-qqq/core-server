package com.ryuqq.core.api.controller.v1.product.validator;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.domain.category.CategoryFinder;

@Component
public class CategoryValidator implements Validator<ProductGroupContextCommandRequestDto>{

	private final CategoryFinder categoryFinder;

	public CategoryValidator(CategoryFinder categoryFinder) {
		this.categoryFinder = categoryFinder;
	}

	@Override
	public void validate(ProductGroupContextCommandRequestDto target, ValidationResult result, boolean updated) {
		boolean b = categoryFinder.existById(target.getCategoryId());
		if(!b){
			result.addError(String.format("Category Id Not Exist : %s", target.getCategoryId()));
		}
	}

}
