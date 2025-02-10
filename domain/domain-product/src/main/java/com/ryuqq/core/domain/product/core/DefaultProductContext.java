package com.ryuqq.core.domain.product.core;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultProductContext implements ProductContext {

	private final DefaultProduct defaultProduct;
	private final List<DefaultOptionContext> options;
	private final boolean deleted;

	public DefaultProductContext(DefaultProduct defaultProduct, List<DefaultOptionContext> options, boolean deleted) {
		this.defaultProduct = defaultProduct;
		this.options = options;
		this.deleted = deleted;
	}

	@Override
	public Product getProduct() {
		return defaultProduct;
	}

	@Override
	public List<? extends OptionContext> getOptions() {
		return options;
	}

	@Override
	public String getOptionNameValue(){
		return options.stream()
			.map(DefaultOptionContext::getOptionNameValue)
			.collect(Collectors.joining(","));
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	protected DefaultProductContext deleted(){
		return new DefaultProductContext(defaultProduct, options, true);
	}


	protected DefaultProductContext assignProductGroupId(Long productGroupId) {
		DefaultProduct assignedDefaultProduct = this.defaultProduct.assignProductGroupId(productGroupId);
		return new DefaultProductContext(assignedDefaultProduct, options, deleted);
	}

	protected DefaultProductContext assignProduct(DefaultProduct newDefaultProduct) {
		DefaultProduct updatedDefaultProduct = newDefaultProduct.assignIdAndProductGroupId(defaultProduct.getId(), defaultProduct.getProductGroupId());
		return new DefaultProductContext(updatedDefaultProduct, options, deleted);
	}

	protected DefaultProductContext assignOptions(List<DefaultOptionContext> newOptions) {
		return new DefaultProductContext(this.defaultProduct, newOptions, this.deleted);
	}


}
