package com.ryuqq.core.storage.db.product.image;

import java.util.List;

import com.ryuqq.core.domain.product.ProductGroupImage;


public class ProductImageDomainMapper {

	public static List<ProductGroupImage> toProductGroupImages(List<ProductGroupImageDto> images) {
		return images.stream()
			.map(dto ->
				ProductGroupImage.create(
					dto.getId(),
					dto.getProductGroupId(),
					dto.getProductImageType(),
					dto.getImageUrl(),
					dto.getOriginUrl(),
					dto.isDeleted())
			)
			.toList();
	}


}
