package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ProductContextInterface {

	List<? extends Sku> fetchByProductGroupId(Long productGroupId);
	List<? extends Sku> fetchByProductGroupId(List<Long> productGroupIds);

}
