package com.ryuqq.core.domain.brand.core;

import java.util.List;

public interface BrandQueryInterface {
	boolean existById(long id);
	Brand fetchById(long id);
	List<? extends Brand> fetchByIds(List<Long> ids);

}
