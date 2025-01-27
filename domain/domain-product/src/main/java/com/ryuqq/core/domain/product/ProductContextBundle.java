package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductContextBundle {

	private final List<ProductContext> products;

	public ProductContextBundle(List<ProductContext> products) {
		if (products == null) {
			products = new ArrayList<>();
		}
		this.products = products;
	}

	public List<ProductContext> getProducts() {
		return products;
	}

	public ProductContextBundle assignProductGroupId(Long productGroupId) {
		List<ProductContext> updatedProducts = products.stream()
			.map(product -> product.assignProductGroupId(productGroupId))
			.toList();
		return new ProductContextBundle(updatedProducts);
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		ProductContextBundle that = (ProductContextBundle) object;
		return Objects.equals(products, that.products);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(products);
	}

	@Override
	public String toString() {
		return "ProductContextBundle{"
			+
			"products="
			+ products
			+
			'}';
	}
}
