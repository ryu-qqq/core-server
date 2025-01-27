package com.ryuqq.core.domain.external;

import java.util.List;
import java.util.Objects;

import com.ryuqq.core.enums.SiteName;

public class ExternalProductGroupContext {

	private final ExternalProductGroup externalProduct;
	private final List<ExternalProduct> externalProducts;
	private final ExternalBrand externalBrand;
	private final ExternalCategory externalCategory;

	private ExternalProductGroupContext(ExternalProductGroup externalProduct, List<ExternalProduct> externalProducts,
									   ExternalBrand externalBrand, ExternalCategory externalCategory) {
		this.externalProduct = externalProduct;
		this.externalProducts = externalProducts;
		this.externalBrand = externalBrand;
		this.externalCategory = externalCategory;
	}

	public static ExternalProductGroupContext create(ExternalProductGroup externalProduct, List<ExternalProduct> externalProducts) {
		return new ExternalProductGroupContext(externalProduct, externalProducts, null, null);
	}


	public static ExternalProductGroupContext create(ExternalProductGroup externalProduct, List<ExternalProduct> externalProducts, ExternalBrand externalBrand, ExternalCategory externalCategory) {
		return new ExternalProductGroupContext(externalProduct, externalProducts, externalBrand, externalCategory);
	}

	public SiteName getSiteName(){
		return externalProduct.getSiteName();
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		ExternalProductGroupContext that = (ExternalProductGroupContext) object;
		return Objects.equals(externalProduct, that.externalProduct)
			&& Objects.equals(externalProducts, that.externalProducts)
			&& Objects.equals(externalBrand, that.externalBrand)
			&& Objects.equals(externalCategory, that.externalCategory);
	}

	@Override
	public int hashCode() {
		return Objects.hash(externalProduct, externalProducts, externalBrand, externalCategory);
	}

	@Override
	public String toString() {
		return "ExternalProductGroupContext{"
			+
			"externalProduct="
			+ externalProduct
			+
			", externalProducts="
			+ externalProducts
			+
			", externalBrand="
			+ externalBrand
			+
			", externalCategory="
			+ externalCategory
			+
			'}';
	}
}
