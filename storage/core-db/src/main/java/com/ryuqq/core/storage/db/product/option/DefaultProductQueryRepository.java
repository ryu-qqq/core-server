package com.ryuqq.core.storage.db.product.option;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.core.DefaultProductOptionContext;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.domain.product.dao.options.ProductQueryRepository;

@Repository
public class DefaultProductQueryRepository implements ProductQueryRepository {

	private final ProductDomainMapper productDomainMapper;
	private final ProductQueryDslQueryRepository productQueryDslQueryRepository;

	public DefaultProductQueryRepository(ProductDomainMapper productDomainMapper, ProductQueryDslQueryRepository productQueryDslQueryRepository) {
		this.productDomainMapper = productDomainMapper;
		this.productQueryDslQueryRepository = productQueryDslQueryRepository;
	}

	@Override
	public DefaultProductOptionContext fetchByProductGroupId(long productGroupId) {
		List<ProductContextDto> productContextDtos = productQueryDslQueryRepository.fetchByProductGroupIds(List.of(productGroupId));
		return productDomainMapper.toDomain(productContextDtos);
	}

	@Override
	public List<? extends ProductOptionContext> fetchByProductGroupIds(List<Long> productGroupIds) {
		List<ProductContextDto> productContextDtos = productQueryDslQueryRepository.fetchByProductGroupIds(productGroupIds);
		return productDomainMapper.toDomains(productContextDtos);

	}
}
