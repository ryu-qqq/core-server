package com.ryuqq.core.domain.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.ryuqq.core.domain.product.core.Sku;
import com.ryuqq.core.domain.product.core.Variant;

public class ProductContext implements Sku {

	private final Product product;
	private final List<OptionContext> options;
	private final boolean deleted;

	public ProductContext(Product product, List<OptionContext> options, boolean deleted) {
		this.product = product;
		this.options = options;
		this.deleted = deleted;
	}

	@Override
	public Long getId() {
		return product.getId();
	}

	@Override
	public List<? extends Variant> getVariants() {
		return options;
	}

	@Override
	public int getQuantity(){
		return product.getQuantity();
	}

	@Override
	public boolean isSoldOut() {
		return product.isSoldOut();
	}

	@Override
	public boolean isDisplayed() {
		return product.isDisplayed();
	}

	@Override
	public BigDecimal getAdditionalPrice() {
		return product.getAdditionalPrice();
	}

	@Override
	public Long getProductGroupId(){
		return product.getProductGroupId();
	}


	protected Product getProduct() {
		return product;
	}

	protected List<OptionContext> getOptions() {
		return options;
	}

	protected String getOptionNameValue(){
		return options.stream()
			.map(OptionContext::getOptionNameValue)
			.collect(Collectors.joining(","));
	}

	protected ProductContext deleted(){
		return new ProductContext(product, options, true);
	}

	protected boolean isDeleted() {
		return deleted;
	}

	protected ProductContext assignProductGroupId(Long productGroupId) {
		Product assignedProduct = this.product.assignProductGroupId(productGroupId);
		return new ProductContext(assignedProduct, options, deleted);
	}

	protected ProductContext assignProduct(Product newProduct) {
		Product updatedProduct = newProduct.assignIdAndProductGroupId(product.getId(), product.getProductGroupId());
		return new ProductContext(updatedProduct, options, deleted);
	}

	protected ProductContext assignOptions(List<OptionContext> newOptions) {
		return new ProductContext(this.product, newOptions, this.deleted);
	}


}
