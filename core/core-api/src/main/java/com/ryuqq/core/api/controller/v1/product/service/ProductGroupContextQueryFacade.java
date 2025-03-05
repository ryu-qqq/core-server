package com.ryuqq.core.api.controller.v1.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ryuqq.core.api.controller.v1.product.mapper.ProductGroupContextResponseMapperProvider;
import com.ryuqq.core.api.controller.v1.product.request.ProductGroupSearchConditionRequestDto;
import com.ryuqq.core.api.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.enums.RequesterType;

@Service
public class ProductGroupContextQueryFacade {

	private final ProductGroupContextQueryService productGroupContextQueryService;
	private final ProductGroupRelatedQueryService productGroupRelatedQueryService;
	private final ProductGroupContextResponseMapperProvider productGroupContextResponseMapperProvider;

	public ProductGroupContextQueryFacade(ProductGroupContextQueryService productGroupContextQueryService,
										  ProductGroupRelatedQueryService productGroupRelatedQueryService,
										  ProductGroupContextResponseMapperProvider productGroupContextResponseMapperProvider) {
		this.productGroupContextQueryService = productGroupContextQueryService;
		this.productGroupRelatedQueryService = productGroupRelatedQueryService;
		this.productGroupContextResponseMapperProvider = productGroupContextResponseMapperProvider;
	}

	public ProductGroupContextResponse fetchByIdForRequester(Long id, RequesterType requesterType) {
		ProductGroupContext productGroupContext = productGroupContextQueryService.fetchById(id);
		ProductGroup productGroup = productGroupContext.getProductGroup();

		Brand brand = productGroupRelatedQueryService.fetchBrandById(productGroup.getBrandId());
		List<? extends Category> categories = productGroupRelatedQueryService.fetchCategoriesById(productGroup.getCategoryId());
		Seller seller = productGroupRelatedQueryService.fetchSellerById(productGroup.getSellerId());

		return productGroupContextResponseMapperProvider
			.getMapper(requesterType)
			.toResponseDto(productGroupContext, brand, categories, seller);
	}


	public <T> T fetchByConditionForRequester(
		ProductGroupSearchConditionRequestDto productGroupSearchConditionRequestDto, RequesterType requesterType) {
		ProductGroupSearchCondition productGroupSearchCondition = productGroupSearchConditionRequestDto.toProductGroupSearchCondition();

		long count = productGroupContextQueryService.countByCondition(productGroupSearchCondition);

		if(count ==0) return (T) List.of();

		List<? extends ProductGroupContext> productGroupContexts = productGroupContextQueryService.fetchByCondition(
			productGroupSearchCondition);

		Map<String, List<Long>> stringListMap = extractIds(productGroupContexts);

		int size = productGroupSearchCondition.getSize();
		Map<Long, Brand> brandMap = productGroupRelatedQueryService.fetchBrandById(stringListMap.get("brandIds"));
		Map<Long, List<Category>> categories = productGroupRelatedQueryService.fetchCategoryHierarchyByIds(stringListMap.get("categoryIds"));
		Map<Long, Seller> sellerMap = productGroupRelatedQueryService.fetchSellerById(stringListMap.get("sellerIds"));

		return (T) productGroupContextResponseMapperProvider
			.getMapper(requesterType)
			.toResponseDto(productGroupContexts, brandMap, categories, sellerMap, size, count);
	}


	private Map<String, List<Long>> extractIds(List<? extends ProductGroupContext> productGroupContexts) {
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


}
