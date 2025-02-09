package com.ryuqq.core.storage.db.brand;

import com.ryuqq.core.domain.brand.dao.BrandSnapshot;
import com.ryuqq.core.storage.db.brand.dto.BrandDto;

public class BrandSnapshotMapper {

	public static BrandSnapshot toSnapshot(BrandDto brandDto) {
		return new BrandSnapshot(
			brandDto.getId(),
			brandDto.getBrandName(),
			brandDto.getBrandNameKr(),
			brandDto.isDisplayed()
		);
	}
}
