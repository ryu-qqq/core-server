package com.ryuqq.core.api.controller.v1.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.category.CategoryFinder;
import com.ryuqq.core.domain.category.DefaultCategory;

@Service
public class CategoryQueryService {

	private final CategoryFinder categoryFinder;

	public CategoryQueryService(CategoryFinder categoryFinder) {
		this.categoryFinder = categoryFinder;
	}


	public List<DefaultCategory> fetchById(long categoryId){
		return categoryFinder.fetchRecursiveByIds(categoryId, true);
	}



}
