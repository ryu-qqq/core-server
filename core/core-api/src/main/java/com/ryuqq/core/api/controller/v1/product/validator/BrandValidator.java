package com.ryuqq.core.api.controller.v1.product.validator;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.domain.brand.BrandFinder;

@Component
public class BrandValidator implements Validator<ProductGroupContextCommandRequestDto> {

	private final BrandFinder brandFinder;

	public BrandValidator(BrandFinder brandFinder) {
		this.brandFinder = brandFinder;
	}

	@Override
	public void validate(ProductGroupContextCommandRequestDto target, ValidationResult result, boolean updated) {
		boolean b = brandFinder.existById(target.getBrandId());
		if(!b){
			result.addError(String.format("Brand Id Not Exist : %s", target.getBrandId()));
		}
	}

}
