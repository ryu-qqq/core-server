package com.ryuqq.core.domain.brand.dao;

import java.util.List;

public interface BrandQueryRepository {

	boolean existById(long brandId);
	BrandSnapshot fetchById(long brandId);
	List<? extends BrandSnapshot> fetchByIds(List<Long> brandIds);

}
