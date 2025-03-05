package com.ryuqq.core.storage.db.product.group;

import org.springframework.stereotype.Repository;

@Repository
public class DefaultProductGroupQueryRepository extends AbstractProductGroupQueryRepository {

	public DefaultProductGroupQueryRepository(ProductGroupContextDomainMapper productGroupContextDomainMapper,
											  DefaultProductGroupQueryDslRepository defaultProductGroupQueryDslRepository) {
		super(productGroupContextDomainMapper, defaultProductGroupQueryDslRepository);
	}

	@Override
	public boolean simpleQuery() {
		return true;
	}

}
