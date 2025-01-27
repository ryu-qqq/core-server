package com.ryuqq.core.domain.product;

import java.util.Objects;

import com.ryuqq.core.domain.product.core.Variant;
import com.ryuqq.core.enums.OptionName;

public class OptionContext implements Variant {

	private final Long productOptionId;
	private final Long optionGroupId;
	private final Long optionDetailId;
	private final Long productId;
	private final OptionName optionName;
	private final String optionValue;
	private final boolean deleted;

	private OptionContext(Long productOptionId, Long optionGroupId, Long optionDetailId, Long productId, OptionName optionName, String optionValue,
						  boolean deleted) {
		this.productOptionId = productOptionId;
		this.optionGroupId = optionGroupId;
		this.optionDetailId = optionDetailId;
		this.productId = productId;
		this.optionName = optionName;
		this.optionValue = optionValue;
		this.deleted = deleted;
	}

	public static OptionContext create(OptionName optionName, String optionValue){
		return new OptionContext(null, null, null, null,optionName, optionValue, false);
	}

	public static OptionContext create(Long productOptionId, Long optionGroupId, Long optionDetailId, Long productId, OptionName optionName, String optionValue){
		return new OptionContext(productOptionId, optionGroupId, optionDetailId, productId, optionName, optionValue, false);
	}

	@Override
	public OptionName getOptionName() {
		return optionName;
	}

	@Override
	public String getOptionValue() {
		return optionValue;
	}

	protected OptionContext assignedOptionGroupId(Long optionGroupId){
		return new OptionContext(productOptionId, optionGroupId, optionDetailId, productId, optionName, optionValue, false);
	}

	protected OptionContext assignedOptionDetailId(Long optionDetailId){
		return new OptionContext(productOptionId, optionGroupId, optionDetailId, productId, optionName, optionValue, false);
	}

	protected String getOptionNameValue(){
		return optionName + " " + optionValue;
	}

	protected Long getOptionGroupId() {
		return optionGroupId;
	}

	protected Long getOptionDetailId() {
		return optionDetailId;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		OptionContext that = (OptionContext) object;
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
