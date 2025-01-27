package com.ryuqq.core.storage.db.category;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.category.CategoryQueryRepository;
import com.ryuqq.core.domain.category.DefaultCategory;

@Repository
public class DefaultCategoryQueryRepository implements CategoryQueryRepository {

	private final CategoryDomainMapper categoryDomainMapper;
	private final CategoryQueryDslRepository categoryQueryDslRepository;

	public DefaultCategoryQueryRepository(CategoryDomainMapper categoryDomainMapper, CategoryQueryDslRepository categoryQueryDslRepository) {
		this.categoryDomainMapper = categoryDomainMapper;
		this.categoryQueryDslRepository = categoryQueryDslRepository;
	}

	@Override
	public boolean existById(long categoryId) {
		return categoryQueryDslRepository.existById(categoryId);
	}

	@Override
	public List<DefaultCategory> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation) {
		return categoryQueryDslRepository.fetchRecursiveByIds(categoryIds, isParentRelation)
			.stream()
			.map(categoryDomainMapper::toDomain)
			.toList();
	}
}
