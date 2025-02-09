package com.ryuqq.core.domain.product.core;

public interface ProductGroupContextCommandInterface {

	long save(ProductGroupContextCommand productGroupContextCommand);

	long update(long id, ProductGroupContextCommand productGroupContextCommand);

}
