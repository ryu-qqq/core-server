package com.ryuqq.core.storage.db.product.notice;


import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.Origin;

public class ProductNoticeDto {
	private final long productGroupId;
    private final String material;
    private final String color;
    private final String size;
    private final String maker;
    private final Origin origin;
    private final String washingMethod;
    private final String yearMonth;
    private final String assuranceStandard;
    private final String asPhone;

    @QueryProjection
    public ProductNoticeDto(long productGroupId, String material, String color, String size, String maker, Origin origin, String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
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

	public long getProductGroupId() {
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
