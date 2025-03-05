package com.ryuqq.core.api.controller.v1.product.response;

import java.util.List;

import com.ryuqq.core.domain.product.core.OptionContext;
import com.ryuqq.core.domain.product.core.Product;
import com.ryuqq.core.domain.product.core.ProductContext;

public record ProductResponseDto(
	long id,
	long productGroupId,
	int quantity,
	boolean soldOut,
	boolean displayed,
	double additionalPrice,
	boolean deleted,
	List<OptionResponseDto> options
 ) {

	public static ProductResponseDto from(ProductContext productContext) {
		Product product = productContext.getProduct();
		List<? extends OptionContext> options = productContext.getOptions();
		return new ProductResponseDto(
			product.getId(),
			product.getProductGroupId(),
			product.getQuantity(),
			product.isSoldOut(),
			product.isDisplayed(),
			product.getAdditionalPrice().longValue(),
			product.isDeleted(),
			toOptionResponseDto(options)
		);
	}


	private static List<OptionResponseDto> toOptionResponseDto(List<? extends OptionContext> options){
		return options.stream()
			.map(OptionResponseDto::from)
			.toList();
	}


}
