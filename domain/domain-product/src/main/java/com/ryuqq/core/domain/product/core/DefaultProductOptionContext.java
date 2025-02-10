package com.ryuqq.core.domain.product.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ryuqq.core.enums.OptionType;

public class DefaultProductOptionContext implements ProductOptionContext {
	private final long productGroupId;
	private final OptionType optionType;
	private final List<DefaultProductContext> products;

	public DefaultProductOptionContext(long productGroupId, OptionType optionType, List<DefaultProductContext> products) {
		this.productGroupId = productGroupId;
		this.optionType = optionType;
		if (products == null) {
			products = new ArrayList<>();
		}
		this.products = products;
	}

	@Override
	public long getProductGroupId() {
		return productGroupId;
	}

	@Override
	public OptionType getOptionType() {
		return optionType;
	}

	@Override
	public List<? extends ProductContext> getProducts() {
		return products;
	}

	public DefaultProductOptionContext assignProductGroupId(Long productGroupId) {
		List<DefaultProductContext> updatedProducts = products.stream()
			.map(product -> product.assignProductGroupId(productGroupId))
			.toList();
		return new DefaultProductOptionContext(productGroupId, optionType, updatedProducts);
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		DefaultProductOptionContext that = (DefaultProductOptionContext) object;
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
