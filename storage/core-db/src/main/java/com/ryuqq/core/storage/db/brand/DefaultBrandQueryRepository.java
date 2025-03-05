package com.ryuqq.core.storage.db.brand;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.brand.dao.BrandQueryRepository;
import com.ryuqq.core.domain.brand.dao.BrandSnapshot;
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
	public BrandSnapshot fetchById(long brandId) {
		return brandQueryDslRepository.fetchById(brandId)
			.map(BrandSnapshotMapper::toSnapshot)
			.orElseThrow(() -> new DataNotFoundException(String.format("Brand Not found brand Id : %s", brandId)));
	}

	@Override
	public List<? extends BrandSnapshot> fetchByIds(List<Long> brandIds) {
		return brandQueryDslRepository.fetchByIds(brandIds).stream()
			.map(BrandSnapshotMapper::toSnapshot)
			.toList();
	}

}
