package com.ryuqq.core.domain.seller.core;

import java.util.List;

public interface SellerQueryInterface {

	boolean existById(long id);
	Seller fetchById(long id);
	List<? extends Seller> fetchByIds(List<Long> ids);

}
