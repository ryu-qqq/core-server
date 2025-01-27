package com.ryuqq.core.storage.db.brand;


import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.brand.BrandQueryRepository;
import com.ryuqq.core.domain.brand.DefaultBrand;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class DefaultBrandQueryRepository implements BrandQueryRepository {

	private final BrandQueryDslRepository brandQueryDslRepository;

	public DefaultBrandQueryRepository(BrandQueryDslRepository brandQueryDslRepository) {
		this.brandQueryDslRepository = brandQueryDslRepository;
	}

	@Override
	public boolean existById(long brandId) {
		return brandQueryDslRepository.existById(brandId);
	}

	@Override
	public DefaultBrand fetchById(long brandId) {
		return brandQueryDslRepository.fetchById(brandId)
			.map(b -> new DefaultBrand(
				b.getId(),
				b.getBrandName(),
				b.getBrandNameKr(),
				b.isDisplayed()
			))
			.orElseThrow(() -> new DataNotFoundException(String.format("Brand Not found brand Id : %s", brandId)));
	}

}
