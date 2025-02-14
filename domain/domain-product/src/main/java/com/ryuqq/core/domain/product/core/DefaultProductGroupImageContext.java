package com.ryuqq.core.domain.product.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultProductGroupImageContext implements ProductGroupImageContext {
	private final Long productGroupId;
	private final List<DefaultProductGroupImage> images;

	public DefaultProductGroupImageContext(Long productGroupId, List<DefaultProductGroupImage> images) {
		if (images == null ) {
			images = new ArrayList<>();
		}
		this.productGroupId = productGroupId;
		this.images = images;
	}

	public Long getProductGroupId() {
		return productGroupId;
	}

	public List<DefaultProductGroupImage> getImages() {
		return images;
	}

	public DefaultProductGroupImageContext assignProductGroupId(Long productGroupId) {
		List<DefaultProductGroupImage> updatedImages = images.stream()
			.map(image -> image.assignProductGroupId(productGroupId))
			.toList();
		return new DefaultProductGroupImageContext(productGroupId, updatedImages);
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
