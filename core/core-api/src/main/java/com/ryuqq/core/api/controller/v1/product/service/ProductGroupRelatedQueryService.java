package com.ryuqq.core.api.controller.v1.product.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.brand.core.BrandSearchCondition;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.category.core.CategoryQueryInterface;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.domain.seller.core.SellerQueryInterface;

@Service
public class ProductGroupRelatedQueryService {

	private final BrandQueryInterface brandQueryInterface;
	private final CategoryQueryInterface categoryQueryInterface;
	private final SellerQueryInterface sellerQueryInterface;

	public ProductGroupRelatedQueryService(BrandQueryInterface brandQueryInterface,
										   CategoryQueryInterface categoryQueryInterface,
										   SellerQueryInterface sellerQueryInterface) {
		this.brandQueryInterface = brandQueryInterface;
		this.categoryQueryInterface = categoryQueryInterface;
		this.sellerQueryInterface = sellerQueryInterface;
	}

	public Brand fetchBrandById(long brandId) {
		return brandQueryInterface.fetchById(brandId);
	}

	public Map<Long, Brand> fetchBrandById(List<Long> brandIds) {

		BrandSearchCondition brandSearchCondition = BrandSearchCondition.builder().brandIds(brandIds).build();
		List<? extends Brand> brands = brandQueryInterface.fetchByCondition(brandSearchCondition);
		return brands.stream()
			.collect(Collectors.toMap(Brand::id, Function.identity(),
				(v1, v2) -> v1));
	}

	public List<? extends Category> fetchCategoriesById(long categoryId) {
		return categoryQueryInterface.fetchRecursiveByIds(categoryId, false);
	}

	public Map<Long, List<Category>> fetchCategoryHierarchyByIds(List<Long> categoryIds) {
		List<? extends Category> categories = categoryQueryInterface.fetchRecursiveByIds(categoryIds, false);

		Map<Long, Category> categoryMap = categories.stream()
			.collect(Collectors.toMap(Category::getId, Function.identity(),
				(v1, v2) -> v1));

		Map<Long, List<Category>> hierarchyMap = new HashMap<>();

		for (Category category : categories) {
			List<Category> hierarchy = Arrays.stream(category.getPath().split(","))
				.map(Long::parseLong)
				.map(categoryMap::get)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

			hierarchyMap.put(category.getId(), hierarchy);
		}

		return hierarchyMap;
	}

	public Seller fetchSellerById(long sellerId) {
		return sellerQueryInterface.fetchById(sellerId);
	}

	public Map<Long, Seller> fetchSellerById(List<Long> sellerIds) {
		List<? extends Seller> sellers = sellerQueryInterface.fetchByIds(sellerIds);
		return sellers.stream()
			.collect(Collectors.toMap(Seller::id, Function.identity(),
				(v1, v2) -> v1));
	}


}
