package com.ryuqq.core.domain.brand;

public interface BrandQueryRepository {

	boolean existById(long brandId);
	DefaultBrand fetchById(long brandId);

}
