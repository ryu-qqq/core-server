package com.ryuqq.core.api.controller.v1.product.request;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ryuqq.core.api.controller.v1.product.service.ProductGroupSearchConditionAnalyzer;
import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.ProductSearchField;
import com.ryuqq.core.enums.ProductSortField;
import com.ryuqq.core.enums.Sort;

public record ProductGroupSearchConditionRequestDto(

	Integer page,
	Integer size,
	List<Long> sellerIds,
	List<Long> productGroupIds,
	List<Long> categoryIds,
	List<Long> brandIds,
	ManagementType managementType,
	Boolean displayed,
	Boolean soldOut,
	Long minSalePrice,
	Long maxSalePrice,
	Long minDiscountRate,
	Long maxDiscountRate,
	Sort sort,
	ProductSortField productSortField,
	Long cursorId,
	ProductSearchField productSearchField,
	String searchWord,
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createdAtFrom,
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createdAtTo,
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime updatedAtFrom,
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime updatedAtTo
) {
	public ProductGroupSearchCondition toProductGroupSearchCondition() {

		if ((createdAtFrom != null && createdAtTo != null) && (updatedAtFrom != null && updatedAtTo != null)) {
			throw new IllegalArgumentException("Either 'createdAt' or 'updatedAt' should be provided, but not both.");
		}

		return ProductGroupSearchCondition.builder()
			.page(page != null ? page : 0)
			.size(size != null ? size : 20)
			.sellerIds(sellerIds != null ? sellerIds : List.of())
			.productGroupIds(productGroupIds != null ? productGroupIds : List.of())
			.categoryIds(categoryIds != null ? categoryIds : List.of())
			.brandIds(brandIds != null ? brandIds : List.of())
			.managementType(managementType)
			.displayed(displayed)
			.soldOut(soldOut)
			.minSalePrice(minSalePrice)
			.maxSalePrice(maxSalePrice)
			.minDiscountRate(minDiscountRate)
			.maxDiscountRate(maxDiscountRate)
			.sort(sort != null ? sort : Sort.DESC)
			.productSortField(productSortField != null ? productSortField : ProductSortField.ID)
			.cursorId(cursorId)
			.productSearchField(productSearchField)
			.searchWord(searchWord)
			.createdAtFrom(createdAtFrom)
			.createdAtTo(createdAtTo)
			.updatedAtFrom(updatedAtFrom)
			.updatedAtTo(updatedAtTo)
			.simpleQuery(ProductGroupSearchConditionAnalyzer.isSimpleQuery(this))
			.build();
	}

}
