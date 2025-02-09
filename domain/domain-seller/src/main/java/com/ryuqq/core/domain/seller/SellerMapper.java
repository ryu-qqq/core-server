package com.ryuqq.core.domain.seller;

import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.domain.seller.dao.SellerSnapshot;

class SellerMapper {

	static Seller toSeller(SellerSnapshot sellerSnapshot){
		return new DefaultSeller(sellerSnapshot.id(), sellerSnapshot.sellerName());
	}

}
