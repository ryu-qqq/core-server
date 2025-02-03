package com.ryuqq.core.storage.db.product.delivery;


import java.math.BigDecimal;

import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "PRODUCT_DELIVERY")
@Entity
public class ProductDeliveryEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Column(name = "DELIVERY_AREA", length = 50, nullable = false)
    private String deliveryArea;

    @Column(name = "DELIVERY_FEE", nullable = false)
    private BigDecimal deliveryFee;

    @Column(name = "DELIVERY_PERIOD_AVERAGE", nullable = false)
    private int deliveryPeriodAverage;

    @Column(name = "RETURN_METHOD_DOMESTIC", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ReturnMethod returnMethodDomestic;

    @Column(name = "RETURN_COURIER_DOMESTIC", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentCompanyCode returnCourierDomestic;

    @Column(name = "RETURN_CHARGE_DOMESTIC", nullable = false)
    private BigDecimal returnChargeDomestic;

    @Column(name = "RETURN_EXCHANGE_AREA_DOMESTIC", length = 50, nullable = false)
    private String returnExchangeAreaDomestic;

    protected ProductDeliveryEntity() {}

    public ProductDeliveryEntity(long productGroupId, String deliveryArea, BigDecimal deliveryFee, int deliveryPeriodAverage, ReturnMethod returnMethodDomestic, ShipmentCompanyCode returnCourierDomestic, BigDecimal returnChargeDomestic, String returnExchangeAreaDomestic) {
        this.productGroupId = productGroupId;
        this.deliveryArea = deliveryArea;
        this.deliveryFee = deliveryFee;
        this.deliveryPeriodAverage = deliveryPeriodAverage;
        this.returnMethodDomestic = returnMethodDomestic;
        this.returnCourierDomestic = returnCourierDomestic;
        this.returnChargeDomestic = returnChargeDomestic;
        this.returnExchangeAreaDomestic = returnExchangeAreaDomestic;
    }


    protected long getProductGroupId() {
        return productGroupId;
    }

    protected String getDeliveryArea() {
        return deliveryArea;
    }

    protected BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    protected int getDeliveryPeriodAverage() {
        return deliveryPeriodAverage;
    }

    protected ReturnMethod getReturnMethodDomestic() {
        return returnMethodDomestic;
    }

    protected ShipmentCompanyCode getReturnCourierDomestic() {
        return returnCourierDomestic;
    }

    protected BigDecimal getReturnChargeDomestic() {
        return returnChargeDomestic;
    }

    protected String getReturnExchangeAreaDomestic() {
        return returnExchangeAreaDomestic;
    }
}
