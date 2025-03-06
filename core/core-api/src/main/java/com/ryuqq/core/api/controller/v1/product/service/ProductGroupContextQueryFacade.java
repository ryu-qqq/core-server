package com.ryuqq.core.api.controller.v1.product.service;

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

		Map<String, List<Long>> extractedIds = ProductGroupExtractor.extractIds(productGroupContexts);

		int size = productGroupSearchCondition.getSize();
		Map<String, Object> relatedEntities = ProductGroupExtractor.fetchRelatedEntities(productGroupRelatedQueryService, extractedIds);

		return (T) productGroupContextResponseMapperProvider
			.getMapper(requesterType)
			.toResponseDto(
				productGroupContexts,
				(Map<Long, Brand>) relatedEntities.get("brandMap"),
				(Map<Long, List<Category>>) relatedEntities.get("categoryMap"),
				(Map<Long, Seller>) relatedEntities.get("sellerMap"),
				size,
				count
			);
	}

}
