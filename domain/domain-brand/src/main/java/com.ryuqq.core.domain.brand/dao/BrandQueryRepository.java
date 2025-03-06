package com.ryuqq.core.domain.brand.dao;

import java.util.List;

import com.ryuqq.core.domain.brand.core.BrandSearchCondition;

public interface BrandQueryRepository {

	boolean existById(long brandId);
	BrandSnapshot fetchById(long brandId);
	List<? extends BrandSnapshot> fetchByCondition(BrandSearchCondition brandSearchCondition);
	long countByCondition(BrandSearchCondition brandSearchCondition);
}
