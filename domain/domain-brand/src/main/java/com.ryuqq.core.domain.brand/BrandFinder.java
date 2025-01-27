package com.ryuqq.core.domain.brand;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.brand.core.BrandQueryInterface;

@Component
public class BrandFinder implements BrandQueryInterface {

	private final BrandQueryRepository brandQueryRepository;

	public BrandFinder(BrandQueryRepository brandQueryRepository) {
		this.brandQueryRepository = brandQueryRepository;
	}

	public boolean existById(long id){
		return brandQueryRepository.existById(id);
	}


	public DefaultBrand fetchById(long id){
		return brandQueryRepository.fetchById(id);
	}

}
