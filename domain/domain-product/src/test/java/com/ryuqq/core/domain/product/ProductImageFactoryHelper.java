package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.stream.IntStream;

import com.ryuqq.core.enums.ProductImageType;

public class ProductImageFactoryHelper {

	public static ProductGroupImageBundle createExistingImages(int count) {
		List<ProductGroupImage> images = IntStream.rangeClosed(1, count)
			.mapToObj(i -> ProductGroupImage.create(
				(long) i,
				1L,
				i
					% 2
					== 0 ?
					ProductImageType.MAIN :
					ProductImageType.DETAIL,
				"imageUrl-"
					+ i,
				"originUrl-"
					+ i,
				false
			))
			.toList();

		return new ProductGroupImageBundle(images);
	}

}
