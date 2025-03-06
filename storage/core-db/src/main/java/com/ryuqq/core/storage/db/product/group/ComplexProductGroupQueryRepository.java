package com.ryuqq.core.storage.db.product.group;

import org.springframework.stereotype.Repository;

@Repository
public class ComplexProductGroupQueryRepository extends AbstractProductGroupQueryRepository {

	public ComplexProductGroupQueryRepository(ProductGroupContextDomainMapper productGroupContextDomainMapper,
											  ComplexProductGroupQueryDslRepository complexProductGroupQueryDslRepository) {
		super(productGroupContextDomainMapper, complexProductGroupQueryDslRepository);
	}

	@Override
	public boolean simpleQuery() {
		return false;
	}
}
