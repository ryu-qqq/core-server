package com.ryuqq.core.storage.db.product.delivery;

import java.math.BigDecimal;

import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

public class ProductDeliveryDto {

	private final long productGroupId;
    private final String deliveryArea;
    private final BigDecimal deliveryFee;
    private final int deliveryPeriodAverage;
    private final ReturnMethod returnMethodDomestic;
    private final ShipmentCompanyCode returnCourierDomestic;
    private final BigDecimal returnChargeDomestic;
    private final String returnExchangeAreaDomestic;

    @QueryProjection
    public ProductDeliveryDto(long productGroupId, String deliveryArea, BigDecimal deliveryFee, int deliveryPeriodAverage, ReturnMethod returnMethodDomestic, ShipmentCompanyCode returnCourierDomestic, BigDecimal returnChargeDomestic, String returnExchangeAreaDomestic) {
		this.productGroupId = productGroupId;
		this.deliveryArea = deliveryArea;
        this.deliveryFee = deliveryFee;
        this.deliveryPeriodAverage = deliveryPeriodAverage;
        this.returnMethodDomestic = returnMethodDomestic;
        this.returnCourierDomestic = returnCourierDomestic;
        this.returnChargeDomestic = returnChargeDomestic;
        this.returnExchangeAreaDomestic = returnExchangeAreaDomestic;
    }

	public long getProductGroupId() {
		return productGroupId;
	}

	public String getDeliveryArea() {
        return deliveryArea;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public int getDeliveryPeriodAverage() {
        return deliveryPeriodAverage;
    }

    public ReturnMethod getReturnMethodDomestic() {
        return returnMethodDomestic;
    }

    public ShipmentCompanyCode getReturnCourierDomestic() {
        return returnCourierDomestic;
    }

    public BigDecimal getReturnChargeDomestic() {
        return returnChargeDomestic;
    }

    public String getReturnExchangeAreaDomestic() {
        return returnExchangeAreaDomestic;
    }
}
