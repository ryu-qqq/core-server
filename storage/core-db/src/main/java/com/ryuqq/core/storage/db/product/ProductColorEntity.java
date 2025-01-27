package com.ryuqq.core.storage.db.product;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "PRODUCT_COLOR")
@Entity
public class ProductColorEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Column(name = "COLOR_ID", nullable = true)
    private Long colorId;

    @Column(name = "COLOR_NAME", nullable = true, length = 30)
    private String colorName;

    @Column(name = "PERCENTAGE", nullable = true)
    private double percentage;

    protected ProductColorEntity() {}

    public ProductColorEntity(long productGroupId, Long colorId, String colorName, double percentage) {
        this.productGroupId = productGroupId;
        this.colorId = colorId;
        this.colorName = colorName;
        this.percentage = percentage;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public Long getColorId() {
        return colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public double getPercentage() {
        return percentage;
    }
}
