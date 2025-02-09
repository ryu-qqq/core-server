package com.ryuqq.core.domain.seller.core;

public interface SellerQueryInterface {

	boolean existById(long id);
	Seller fetchById(long id);
}
