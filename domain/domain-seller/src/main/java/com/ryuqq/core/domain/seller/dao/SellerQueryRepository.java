package com.ryuqq.core.domain.seller.dao;

public interface SellerQueryRepository {
	boolean existById(long id);
	SellerSnapshot fetchById(long id);
}
