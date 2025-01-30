package com.ryuqq.core.external.buyma;

import java.math.BigDecimal;

public class BuyMaPrice {

	BigDecimal regularPrice;
	BigDecimal currentPrice;

	public BuyMaPrice(BigDecimal currentPrice) {
		this.regularPrice = currentPrice.multiply(BigDecimal.valueOf(1.5));
		this.currentPrice = currentPrice;
	}

	public BigDecimal getRegularPrice() {
		return regularPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
}
