package com.ryuqq.core.domain.seller.dao;

import java.util.List;

public interface SellerQueryRepository {
	boolean existById(long id);
	SellerSnapshot fetchById(long id);
	List<? extends SellerSnapshot> fetchByIds(List<Long> ids);
}
