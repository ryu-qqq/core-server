package com.ryuqq.core.domain.product;

import java.util.Objects;

import com.ryuqq.core.domain.product.core.OptionContext;
import com.ryuqq.core.enums.OptionName;

public class DefaultOptionContext implements OptionContext {

	private final Long productOptionId;
	private final Long optionGroupId;
	private final Long optionDetailId;
	private final Long productId;
	private final OptionName optionName;
	private final String optionValue;
	private final boolean deleted;

	public DefaultOptionContext(Long productOptionId, Long optionGroupId, Long optionDetailId, Long productId, OptionName optionName, String optionValue,
								 boolean deleted) {
		this.productOptionId = productOptionId;
		this.optionGroupId = optionGroupId;
		this.optionDetailId = optionDetailId;
		this.productId = productId;
		this.optionName = optionName;
		this.optionValue = optionValue;
		this.deleted = deleted;
	}


	protected DefaultOptionContext assignedOptionGroupId(Long optionGroupId){
		return new DefaultOptionContext(productOptionId, optionGroupId, optionDetailId, productId, optionName, optionValue, false);
	}

	protected DefaultOptionContext assignedOptionDetailId(Long optionDetailId){
		return new DefaultOptionContext(productOptionId, optionGroupId, optionDetailId, productId, optionName, optionValue, false);
	}

	@Override
	public String getOptionNameValue(){
		return optionName + " " + optionValue;
	}

	@Override
	public Long getProductOptionId() {
		return productOptionId;
	}

	@Override
	public Long getOptionGroupId() {
		return optionGroupId;
	}

	@Override
	public Long getOptionDetailId() {
		return optionDetailId;
	}

	@Override
	public Long getProductId() {
		return productId;
	}

	@Override
	public OptionName getOptionName() {
		return optionName;
	}

	@Override
	public String getOptionValue() {
		return optionValue;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		DefaultOptionContext that = (DefaultOptionContext) object;
		return deleted
			== that.deleted
			&& Objects.equals(productOptionId, that.productOptionId)
			&& Objects.equals(productId, that.productId)
			&& optionName
			== that.optionName
			&& Objects.equals(optionValue, that.optionValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productOptionId, productId, optionName, optionValue, deleted);
	}

	@Override
	public String toString() {
		return "OptionContext{"
			+
			"productOptionId="
			+ productOptionId
			+
			", productId="
			+ productId
			+
			", optionName="
			+ optionName
			+
			", optionValue='"
			+ optionValue
			+ '\''
			+
			", deleted="
			+ deleted
			+
			'}';
	}
}
