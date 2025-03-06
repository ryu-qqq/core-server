package com.ryuqq.core.domain.product.dao.group;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductGroupQueryRepositoryProvider {

	private static final Logger log = LoggerFactory.getLogger(ProductGroupQueryRepositoryProvider.class);

	private final Map<Boolean, ProductGroupQueryRepository> repositoryMap;

	public ProductGroupQueryRepositoryProvider(List<ProductGroupQueryRepository> productGroupQueryRepositories) {
		this.repositoryMap = productGroupQueryRepositories.stream()
			.collect(Collectors.toMap(
				ProductGroupQueryRepository::simpleQuery,
				repo -> repo,
				(existing, duplicate) -> {
					log.warn("Duplicate ProductGroupQueryRepository detected for simpleQuery={}! Using the first registered implementation: {}",
						existing.simpleQuery(), existing.getClass().getSimpleName());
					return existing;
				}
			));
	}

	public ProductGroupQueryRepository getProductGroupQueryRepository(boolean simpleQuery) {
		ProductGroupQueryRepository productGroupQueryRepository = repositoryMap.get(simpleQuery);
		if (productGroupQueryRepository == null) {
			throw new RuntimeException("Can not find product group query repository for simpleQuery=" + simpleQuery);
		}
		return productGroupQueryRepository;
	}


}
