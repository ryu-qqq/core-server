package com.ryuqq.core.domain.product;

import com.ryuqq.core.domain.product.core.ItemNoticeInfo;
import com.ryuqq.core.enums.Origin;

public class ProductNotice implements ItemNoticeInfo {
	private final Long productGroupId;
	private final String material;
	private final String color;
	private final String size;
	private final String maker;
	private final Origin origin;
	private final String washingMethod;
	private final String yearMonth;
	private final String assuranceStandard;
	private final String asPhone;

	private ProductNotice(Long productGroupId, String material, String color, String size, String maker, Origin origin,
						 String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
		this.productGroupId = productGroupId;
		this.material = material;
		this.color = color;
		this.size = size;
		this.maker = maker;
		this.origin = origin;
		this.washingMethod = washingMethod;
		this.yearMonth = yearMonth;
		this.assuranceStandard = assuranceStandard;
		this.asPhone = asPhone;
	}

	public static ProductNotice create(String material, String color, String size, String maker, Origin origin,
									   String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {

		return new ProductNotice(null, material, color, size, maker, origin,
			washingMethod, yearMonth, assuranceStandard, asPhone);
	}

	public static ProductNotice create(long productGroupId, String material, String color, String size, String maker, Origin origin,
									   String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {

		return new ProductNotice(productGroupId,  material, color, size, maker, origin,
			washingMethod, yearMonth, assuranceStandard, asPhone);
	}


	public ProductNotice assignProductGroupId(Long productGroupId) {
		return new ProductNotice(productGroupId, this.material, this.color, this.size,
			this.maker, this.origin, this.washingMethod, this.yearMonth, this.assuranceStandard, this.asPhone);
	}

	public Long getProductGroupId() {
		return productGroupId;
	}

	public String getMaterial() {
		return material;
	}

	public String getColor() {
		return color;
	}

	public String getSize() {
		return size;
	}

	public String getMaker() {
		return maker;
	}

	public Origin getOrigin() {
		return origin;
	}

	public String getWashingMethod() {
		return washingMethod;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public String getAssuranceStandard() {
		return assuranceStandard;
	}

	public String getAsPhone() {
		return asPhone;
	}


}
