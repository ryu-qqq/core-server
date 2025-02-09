package com.ryuqq.core.storage.db.category;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.category.dao.CategoryQueryRepository;
import com.ryuqq.core.domain.category.dao.CategorySnapshot;

@Repository
public class DefaultCategoryQueryRepository implements CategoryQueryRepository {

	private final CategoryQueryDslRepository categoryQueryDslRepository;

	public DefaultCategoryQueryRepository(CategoryQueryDslRepository categoryQueryDslRepository) {
		this.categoryQueryDslRepository = categoryQueryDslRepository;
	}

	@Override
	public boolean existById(long categoryId) {
		return categoryQueryDslRepository.existById(categoryId);
	}

	@Override
	public List<CategorySnapshot> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation) {
		return categoryQueryDslRepository.fetchRecursiveByIds(categoryIds, isParentRelation)
			.stream()
			.map(CategorySnapshotMapper::toSnapshot)
			.toList();
	}

}
