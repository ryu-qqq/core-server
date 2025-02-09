package com.ryuqq.core.domain.brand.core;

public interface BrandQueryInterface {
	boolean existById(long id);
	Brand fetchById(long id);
}
