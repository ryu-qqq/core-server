package com.ryuqq.core.domain.category;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.category.core.CategoryQueryInterface;
import com.ryuqq.core.domain.category.dao.CategoryQueryRepository;

@Component
class CategoryFinder implements CategoryQueryInterface {

	private final CategoryQueryRepository categoryQueryRepository;

	public CategoryFinder(CategoryQueryRepository categoryQueryRepository) {
		this.categoryQueryRepository = categoryQueryRepository;
	}

	@Override
	public boolean existById(long id){
		return categoryQueryRepository.existById(id);
	}

	@Override
	public List<? extends Category> fetchRecursiveByIds(long categoryId, boolean isParentRelation){
		return fetchRecursiveByIds(List.of(categoryId), isParentRelation);
	}

	@Override
	public List<? extends Category> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation){
		return categoryQueryRepository.fetchRecursiveByIds(categoryIds, isParentRelation).stream()
			.map(CategoryMapper::toCategory)
			.toList();
	}

}
