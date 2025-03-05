package com.ryuqq.core.domain.brand;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.brand.dao.BrandQueryRepository;
import com.ryuqq.core.domain.brand.dao.BrandSnapshot;

@Component
class BrandFinder implements BrandQueryInterface {

	private final BrandQueryRepository brandQueryRepository;

	public BrandFinder(BrandQueryRepository brandQueryRepository) {
		this.brandQueryRepository = brandQueryRepository;
	}

	public boolean existById(long id){
		return brandQueryRepository.existById(id);
	}

	@Override
	public Brand fetchById(long id){
		BrandSnapshot brandSnapshot = brandQueryRepository.fetchById(id);
		return BrandMapper.toBrand(brandSnapshot);
	}

	@Override
	public List<? extends Brand> fetchByIds(List<Long> ids) {
		return brandQueryRepository.fetchByIds(ids).stream()
			.map(BrandMapper::toBrand)
			.toList();
	}

}
