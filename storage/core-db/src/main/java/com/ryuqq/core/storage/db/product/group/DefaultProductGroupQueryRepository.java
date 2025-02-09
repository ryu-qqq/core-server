package com.ryuqq.core.storage.db.product.group;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.DefaultProductGroup;
import com.ryuqq.core.domain.product.DefaultProductGroupContext;
import com.ryuqq.core.domain.product.dao.group.ProductGroupQueryRepository;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class DefaultProductGroupQueryRepository implements ProductGroupQueryRepository {

	private final ProductGroupContextDomainMapper productGroupContextDomainMapper;
	private final ProductGroupQueryDslRepository productGroupQueryDslRepository;

	public DefaultProductGroupQueryRepository(ProductGroupContextDomainMapper productGroupContextDomainMapper,
											  ProductGroupQueryDslRepository productGroupQueryDslRepository) {
		this.productGroupContextDomainMapper = productGroupContextDomainMapper;
		this.productGroupQueryDslRepository = productGroupQueryDslRepository;
	}


	@Override
	public DefaultProductGroupContext fetchContextById(long productGroupId) {
		return productGroupQueryDslRepository.fetchContextById(productGroupId)
			.map(productGroupContextDomainMapper::toDomain)
			.orElseThrow(() ->
				new DataNotFoundException(String.format("Product Group Context not found %s", productGroupId)));
	}

	@Override
	public List<DefaultProductGroupContext> fetchContextByIds(List<Long> productGroupIds) {
		return productGroupQueryDslRepository.fetchContextByIds(productGroupIds).stream()
			.map(productGroupContextDomainMapper::toDomain)
			.toList();
	}
	@Override
	public List<DefaultProductGroup> fetchBySellerId(long sellerId){
		return productGroupQueryDslRepository.fetchBySellerId(sellerId).stream()
			.map(productGroupContextDomainMapper::toProductGroupDomain)
			.toList();
	}

}
