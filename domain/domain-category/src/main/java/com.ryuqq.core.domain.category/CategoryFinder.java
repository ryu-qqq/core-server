package com.ryuqq.core.domain.category;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.category.core.CategoryQueryInterface;

@Component
public class CategoryFinder implements CategoryQueryInterface {

	private final CategoryQueryRepository categoryQueryRepository;

	public CategoryFinder(CategoryQueryRepository categoryQueryRepository) {
		this.categoryQueryRepository = categoryQueryRepository;
	}

	public boolean existById(long id){
		return categoryQueryRepository.existById(id);
	}

	@Override
	public List<? extends Category> fetchRecursiveByIds(long categoryId, boolean isParentRelation){
		return categoryQueryRepository.fetchRecursiveByIds(List.of(categoryId), isParentRelation);
	}
}
