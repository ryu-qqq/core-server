package com.ryuqq.core.storage.db.product.image;

import java.util.List;

import com.ryuqq.core.domain.product.core.DefaultProductGroupImage;


public class ProductImageDomainMapper {

	public static List<DefaultProductGroupImage> toProductGroupImages(List<ProductGroupImageDto> images) {
		return images.stream()
			.map(dto ->
				DefaultProductGroupImage.create(
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
