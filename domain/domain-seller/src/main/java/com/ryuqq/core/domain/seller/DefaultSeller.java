package com.ryuqq.core.domain.seller;

import com.ryuqq.core.domain.seller.core.Seller;

public record DefaultSeller(
	long id,
	String sellerName
) implements Seller {

}
