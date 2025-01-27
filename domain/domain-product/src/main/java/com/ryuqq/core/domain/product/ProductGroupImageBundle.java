package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductGroupImageBundle {

	private final List<ProductGroupImage> images;

	public ProductGroupImageBundle(List<ProductGroupImage> images) {
		if (images == null ) {
			images = new ArrayList<>();
		}
		this.images = images;
	}

	public List<ProductGroupImage> getImages() {
		return images;
	}

	public ProductGroupImageBundle assignProductGroupId(Long productGroupId) {
		List<ProductGroupImage> updatedImages = images.stream()
			.map(image -> image.assignProductGroupId(productGroupId))
			.toList();
		return new ProductGroupImageBundle(updatedImages);
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		ProductGroupImageBundle that = (ProductGroupImageBundle) object;
		return Objects.equals(images, that.images);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(images);
	}

	@Override
	public String toString() {
		return "ProductGroupImageBundle{"
			+
			"images="
			+ images
			+
			'}';
	}
}
