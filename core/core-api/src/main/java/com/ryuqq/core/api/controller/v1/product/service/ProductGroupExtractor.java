package com.ryuqq.core.api.controller.v1.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.seller.core.Seller;

public class ProductGroupExtractor {

	private ProductGroupExtractor() {
		throw new IllegalStateException("Utility class");
	}

	public static Map<String, List<Long>> extractIds(List<? extends ProductGroupContext> productGroupContexts) {
		List<Long> brandIds = new ArrayList<>();
		List<Long> categoryIds = new ArrayList<>();
		List<Long> sellerIds = new ArrayList<>();

		for (ProductGroupContext context : productGroupContexts) {
			ProductGroup productGroup = context.getProductGroup();

			brandIds.add(productGroup.getBrandId());
			categoryIds.add(productGroup.getCategoryId());
			sellerIds.add(productGroup.getSellerId());
		}

		Map<String, List<Long>> extractedIds = new HashMap<>();
		extractedIds.put("brandIds", brandIds);
		extractedIds.put("categoryIds", categoryIds);
		extractedIds.put("sellerIds", sellerIds);

		return extractedIds;
	}

	public static Map<String, Object> fetchRelatedEntities(
		ProductGroupRelatedQueryService productGroupRelatedQueryService,
		Map<String, List<Long>> extractedIds) {

		Map<Long, Brand> brandMap = productGroupRelatedQueryService.fetchBrandById(extractedIds.get("brandIds"));
		Map<Long, List<Category>> categoryMap = productGroupRelatedQueryService.fetchCategoryHierarchyByIds(extractedIds.get("categoryIds"));
		Map<Long, Seller> sellerMap = productGroupRelatedQueryService.fetchSellerById(extractedIds.get("sellerIds"));

		Map<String, Object> relatedEntities = new HashMap<>();
		relatedEntities.put("brandMap", brandMap);
		relatedEntities.put("categoryMap", categoryMap);
		relatedEntities.put("sellerMap", sellerMap);

		return relatedEntities;
	}

}
