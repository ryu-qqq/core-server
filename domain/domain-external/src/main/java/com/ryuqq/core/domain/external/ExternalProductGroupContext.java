package com.ryuqq.core.domain.external;

import java.util.List;
import java.util.Objects;

import com.ryuqq.core.enums.SiteName;

public class ExternalProductGroupContext {

	private final ExternalProductGroup externalProduct;
	private final List<ExternalProduct> externalProducts;
	private final DefaultExternalBrandMapping defaultExternalBrandMapping;
	private final DefaultExternalCategoryMapping defaultExternalCategoryMapping;

	private ExternalProductGroupContext(ExternalProductGroup externalProduct, List<ExternalProduct> externalProducts,
										DefaultExternalBrandMapping defaultExternalBrandMapping, DefaultExternalCategoryMapping defaultExternalCategoryMapping) {
		this.externalProduct = externalProduct;
		this.externalProducts = externalProducts;
		this.defaultExternalBrandMapping = defaultExternalBrandMapping;
		this.defaultExternalCategoryMapping = defaultExternalCategoryMapping;
	}

	public static ExternalProductGroupContext create(ExternalProductGroup externalProduct, List<ExternalProduct> externalProducts) {
		return new ExternalProductGroupContext(externalProduct, externalProducts, null, null);
	}


	public static ExternalProductGroupContext create(ExternalProductGroup externalProduct, List<ExternalProduct> externalProducts, DefaultExternalBrandMapping defaultExternalBrandMapping, DefaultExternalCategoryMapping defaultExternalCategoryMapping) {
		return new ExternalProductGroupContext(externalProduct, externalProducts, defaultExternalBrandMapping,
			defaultExternalCategoryMapping);
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
			&& Objects.equals(defaultExternalBrandMapping, that.defaultExternalBrandMapping)
			&& Objects.equals(defaultExternalCategoryMapping, that.defaultExternalCategoryMapping);
	}

	@Override
	public int hashCode() {
		return Objects.hash(externalProduct, externalProducts, defaultExternalBrandMapping,
			defaultExternalCategoryMapping);
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
			+ defaultExternalBrandMapping
			+
			", externalCategory="
			+ defaultExternalCategoryMapping
			+
			'}';
	}
}
