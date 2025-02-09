package com.ryuqq.core.domain.brand;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.dao.BrandSnapshot;

class BrandMapper {

	static Brand toBrand(BrandSnapshot brandSnapshot) {
		return new DefaultBrand(
			brandSnapshot.id(),
			brandSnapshot.brandName(),
			brandSnapshot.brandNameKr(),
			brandSnapshot.displayed()
		);
	}

}
