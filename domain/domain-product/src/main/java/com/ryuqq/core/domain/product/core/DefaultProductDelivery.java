package com.ryuqq.core.domain.product;

import java.math.BigDecimal;
import java.util.Objects;

import com.ryuqq.core.domain.Money;
import com.ryuqq.core.domain.product.core.ProductDelivery;
import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;


public class DefaultProductDelivery implements ProductDelivery {
	private final Long productGroupId;
	private final String deliveryArea;
	private final Money deliveryFee;
	private final int deliveryPeriodAverage;
	private final ReturnMethod returnMethodDomestic;
	private final ShipmentCompanyCode returnCourierDomestic;
	private final Money returnChargeDomestic;
	private final String returnExchangeAreaDomestic;

	private DefaultProductDelivery(Long productGroupId, String deliveryArea, Money deliveryFee, int deliveryPeriodAverage,
								   ReturnMethod returnMethodDomestic, ShipmentCompanyCode returnCourierDomestic,
								   Money returnChargeDomestic, String returnExchangeAreaDomestic) {
		this.productGroupId = productGroupId;
		this.deliveryArea = deliveryArea;
		this.deliveryFee = deliveryFee;
		this.deliveryPeriodAverage = deliveryPeriodAverage;
		this.returnMethodDomestic = returnMethodDomestic;
		this.returnCourierDomestic = returnCourierDomestic;
		this.returnChargeDomestic = returnChargeDomestic;
		this.returnExchangeAreaDomestic = returnExchangeAreaDomestic;
	}

	public static DefaultProductDelivery create(Long productGroupId, String deliveryArea, Money deliveryFee, int deliveryPeriodAverage,
												ReturnMethod returnMethodDomestic, ShipmentCompanyCode returnCourierDomestic,
												Money returnChargeDomestic, String returnExchangeAreaDomestic) {
		return new DefaultProductDelivery(productGroupId, deliveryArea, deliveryFee, deliveryPeriodAverage,
			returnMethodDomestic, returnCourierDomestic, returnChargeDomestic, returnExchangeAreaDomestic);
	}

	public DefaultProductDelivery assignProductGroupId(Long productGroupId) {
		return new DefaultProductDelivery(productGroupId, this.deliveryArea, this.deliveryFee, this.deliveryPeriodAverage,
			this.returnMethodDomestic, this.returnCourierDomestic, this.returnChargeDomestic,
			this.returnExchangeAreaDomestic);
	}

	public Long getProductGroupId() {
		return productGroupId;
	}

	public String getDeliveryArea() {
		return deliveryArea;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee.getAmount();
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
		return returnChargeDomestic.getAmount();
	}

	public String getReturnExchangeAreaDomestic() {
		return returnExchangeAreaDomestic;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		DefaultProductDelivery that = (DefaultProductDelivery) object;
		return deliveryPeriodAverage
			== that.deliveryPeriodAverage
			&& Objects.equals(productGroupId, that.productGroupId)
			&& Objects.equals(deliveryArea, that.deliveryArea)
			&& Objects.equals(deliveryFee, that.deliveryFee)
			&& returnMethodDomestic
			== that.returnMethodDomestic
			&& returnCourierDomestic
			== that.returnCourierDomestic
			&& Objects.equals(returnChargeDomestic, that.returnChargeDomestic)
			&& Objects.equals(returnExchangeAreaDomestic, that.returnExchangeAreaDomestic);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productGroupId, deliveryArea, deliveryFee, deliveryPeriodAverage, returnMethodDomestic,
			returnCourierDomestic, returnChargeDomestic, returnExchangeAreaDomestic);
	}

	@Override
	public String toString() {
		return "ProductDelivery{"
			+
			"productGroupId="
			+ productGroupId
			+
			", deliveryArea='"
			+ deliveryArea
			+ '\''
			+
			", deliveryFee="
			+ deliveryFee
			+
			", deliveryPeriodAverage="
			+ deliveryPeriodAverage
			+
			", returnMethodDomestic="
			+ returnMethodDomestic
			+
			", returnCourierDomestic="
			+ returnCourierDomestic
			+
			", returnChargeDomestic="
			+ returnChargeDomestic
			+
			", returnExchangeAreaDomestic='"
			+ returnExchangeAreaDomestic
			+ '\''
			+
			'}';
	}
}
