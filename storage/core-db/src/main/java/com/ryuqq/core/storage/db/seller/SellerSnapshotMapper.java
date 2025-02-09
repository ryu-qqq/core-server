package com.ryuqq.core.storage.db.seller;

import com.ryuqq.core.domain.seller.dao.SellerSnapshot;
import com.ryuqq.core.storage.db.seller.dto.SellerDto;

public class SellerSnapshotMapper {

	public static SellerSnapshot toSnapshot(SellerDto sellerDto) {
		return new SellerSnapshot(sellerDto.getId(), sellerDto.getSellerName());
	}

}
