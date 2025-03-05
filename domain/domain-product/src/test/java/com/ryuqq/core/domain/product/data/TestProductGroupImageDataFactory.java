package com.ryuqq.core.domain.product.data;

import java.util.List;

import com.ryuqq.core.domain.product.dao.image.DefaultProductGroupImageCommand;
import com.ryuqq.core.domain.product.dao.image.DefaultProductGroupImageContextCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;
import com.ryuqq.core.enums.ProductImageType;

public class TestProductGroupImageDataFactory {

	public static DefaultProductGroupImageCommand createImageCommand(long id, long productGroupId, ProductImageType type, String imageUrl, int displayOrder) {
		return new DefaultProductGroupImageCommand(
			id,
			productGroupId,
			type,
			imageUrl,
			"originUrl",
			displayOrder,
			false
		);
	}

	// List<ProductGroupImageCommand>를 포함하는 ProductGroupImageContextCommand 생성
	public static ProductGroupImageContextCommand createContextCommand(
		List<DefaultProductGroupImageCommand> imageCommands) {
		return new DefaultProductGroupImageContextCommand(imageCommands);
	}

	// 테스트용 메인 이미지가 포함된 ProductGroupImageContextCommand 생성
	public static ProductGroupImageContextCommand createContextWithMainImage() {
		DefaultProductGroupImageCommand mainImage = createImageCommand(1, 123L, ProductImageType.MAIN, "mainImageUrl", 1);
		return createContextCommand(List.of(mainImage));
	}

	// 테스트용 다른 종류의 이미지들 포함된 ProductGroupImageContextCommand 생성
	public static ProductGroupImageContextCommand createContextWithOtherImages() {
		DefaultProductGroupImageCommand otherImage1 = createImageCommand(1, 123L, ProductImageType.DETAIL, "detailImageUrl1", 1);
		DefaultProductGroupImageCommand otherImage2 = createImageCommand(2, 123L, ProductImageType.DETAIL, "detailImageUrl2", 2);
		return createContextCommand(List.of(otherImage1, otherImage2));
	}

	// 테스트용 MAIN 이미지가 없고, 다른 이미지만 포함된 ProductGroupImageContextCommand 생성
	public static ProductGroupImageContextCommand createContextWithoutMainImage() {
		DefaultProductGroupImageCommand otherImage1 = createImageCommand(1, 123L, ProductImageType.DETAIL, "detailImageUrl1", 1);
		DefaultProductGroupImageCommand otherImage2 = createImageCommand(2, 123L, ProductImageType.DETAIL, "detailImageUrl2", 2);
		return createContextCommand(List.of(otherImage1, otherImage2));
	}

	// 여러 개의 MAIN 이미지 포함된 ProductGroupImageContextCommand 생성
	public static ProductGroupImageContextCommand createContextWithMultipleMainImages() {
		DefaultProductGroupImageCommand mainImage1 = createImageCommand(1, 123L, ProductImageType.MAIN, "mainImageUrl1", 1);
		DefaultProductGroupImageCommand mainImage2 = createImageCommand(2, 123L, ProductImageType.MAIN, "mainImageUrl2", 2);
		return createContextCommand(List.of(mainImage1, mainImage2));
	}

}
