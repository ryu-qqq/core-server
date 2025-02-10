package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ryuqq.core.domain.product.core.ProductGroupImageContext;

public class DefaultProductGroupImageContext implements ProductGroupImageContext {

	private final List<DefaultProductGroupImage> images;

	public DefaultProductGroupImageContext(List<DefaultProductGroupImage> images) {
		if (images == null ) {
			images = new ArrayList<>();
		}
		this.images = images;
	}

	public List<DefaultProductGroupImage> getImages() {
		return images;
	}

	public DefaultProductGroupImageContext assignProductGroupId(Long productGroupId) {
		List<DefaultProductGroupImage> updatedImages = images.stream()
			.map(image -> image.assignProductGroupId(productGroupId))
			.toList();
		return new DefaultProductGroupImageContext(updatedImages);
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		DefaultProductGroupImageContext that = (DefaultProductGroupImageContext) object;
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
