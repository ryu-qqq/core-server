package com.ryuqq.core.domain.brand.dao;

public interface BrandQueryRepository {

	boolean existById(long brandId);
	BrandSnapshot fetchById(long brandId);

}
