package com.ryuqq.core.storage.db.product.group;

import java.util.List;

import com.ryuqq.core.domain.product.core.DefaultProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.domain.product.dao.group.ProductGroupQueryRepository;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

public abstract class AbstractProductGroupQueryRepository implements ProductGroupQueryRepository {

	private final ProductGroupContextDomainMapper productGroupContextDomainMapper;
	private final ProductGroupQueryDslRepository productGroupQueryDslRepository;

	protected AbstractProductGroupQueryRepository(ProductGroupContextDomainMapper productGroupContextDomainMapper,
												  ProductGroupQueryDslRepository productGroupQueryDslRepository) {
		this.productGroupContextDomainMapper = productGroupContextDomainMapper;
		this.productGroupQueryDslRepository = productGroupQueryDslRepository;
	}

	public DefaultProductGroupContext fetchContextById(long productGroupId) {
		return productGroupQueryDslRepository.fetchContextById(productGroupId)
			.map(productGroupContextDomainMapper::toDomain)
			.orElseThrow(() ->
				new DataNotFoundException(String.format("Product Group Context not found %s", productGroupId)));
	}

	@Override
	public List<DefaultProductGroupContext> fetchContextByCondition(
		ProductGroupSearchCondition productGroupSearchCondition) {

		return productGroupQueryDslRepository.fetchContextByCondition(productGroupSearchCondition)
			.stream()
			.map(productGroupContextDomainMapper::toDomain)
			.toList();
	}

	@Override
	public long countByCondition(ProductGroupSearchCondition productGroupSearchCondition) {
		return productGroupQueryDslRepository.countByCondition(productGroupSearchCondition);
	}
}
