package com.ryuqq.core.api.controller.v1.product.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductStatus;

public record DefaultProductGroupResponseDto(
	long id,
	long sellerId,
	String sellerName,
	long categoryId,
	long brandId,
	String productGroupName,
	String styleCode,
	ProductCondition productCondition,
	ManagementType managementType,
	OptionType optionType,
	PriceResponseDto price,
	boolean soldOut,
	boolean displayed,
	ProductStatus productStatus,
	String keyword,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createAt,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime updateAt
) {

	public static DefaultProductGroupResponseDto from(ProductGroup productGroup, Seller seller) {
		return new DefaultProductGroupResponseDto(
			productGroup.getId(),
			seller.id(),
			seller.sellerName(),
			productGroup.getCategoryId(),
			productGroup.getBrandId(),
			productGroup.getProductGroupName(),
			productGroup.getStyleCode(),
			productGroup.getProductCondition(),
			productGroup.getManagementType(),
			productGroup.getOptionType(),
			PriceResponseDto.from(productGroup.getPrice()),
			productGroup.isSoldOut(),
			productGroup.isDisplayed(),
			productGroup.getProductStatus(),
			productGroup.getKeyword(),
			productGroup.getCreateAt(),
			productGroup.getUpdateAt()
		);
	}
}
