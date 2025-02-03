package com.ryuqq.core.api.controller.v1.brand;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.brand.BrandFinder;
import com.ryuqq.core.domain.brand.DefaultBrand;

@Component
public class BrandQueryService {

	private final BrandFinder brandFinder;

	public BrandQueryService(BrandFinder brandFinder) {
		this.brandFinder = brandFinder;
	}

	public DefaultBrand fetchById(long brandId){
		return brandFinder.fetchById(brandId);
	}
}
