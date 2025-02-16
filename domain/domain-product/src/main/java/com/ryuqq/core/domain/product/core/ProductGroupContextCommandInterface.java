package com.ryuqq.core.domain.product.core;

import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;

public interface ProductGroupContextCommandInterface {

	long save(ProductGroupContextCommand productGroupContextCommand);

	long update(long id, ProductGroupContextCommand productGroupContextCommand);

}
