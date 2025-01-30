package com.ryuqq.core.domain.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ryuqq.core.domain.Money;
import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.core.Price;
import com.ryuqq.core.enums.ErrorType;

public class DefaultPrice implements Price {

	private final Money regularPrice;
	private final Money currentPrice;
	private final Money salePrice;
	private final Money directDiscountPrice;
	private final int directDiscountRate;
	private final int discountRate;

	private DefaultPrice(Money regularPrice, Money currentPrice, Money salePrice,
						 Money directDiscountPrice, int directDiscountRate, int discountRate) {

		if (regularPrice == null || currentPrice == null) {
			throw new DomainException(ErrorType.BAD_REQUEST_ERROR, "Prices cannot be null.");
		}

		if (regularPrice.isLessThan(currentPrice)) {
			throw new DomainException(ErrorType.BAD_REQUEST_ERROR,  "Regular price cannot be less than current price.");
		}

		this.regularPrice = regularPrice;
		this.currentPrice = currentPrice;
		this.salePrice = salePrice;
		this.directDiscountPrice = directDiscountPrice;
		this.directDiscountRate = directDiscountRate;
		this.discountRate = discountRate;
	}

	public static DefaultPrice create(BigDecimal regularPrice, BigDecimal currentPrice) {
		Money regularMoney = Money.wons(regularPrice);
		Money currentMoney = Money.wons(currentPrice);

		int discountRate = calculateDiscountRate(regularMoney, currentMoney);
		return new DefaultPrice(
			regularMoney,
			currentMoney,
			currentMoney,
			Money.ZERO,
			0,
			discountRate
		);
	}


	private static int calculateDiscountRate(Money basePrice, Money salePrice) {
		Money discount = basePrice.minus(salePrice);
		BigDecimal discountRate = discount.divide(basePrice, 2, RoundingMode.HALF_UP);
		return discountRate.multiply(BigDecimal.valueOf(100)).intValueExact();
	}


	public BigDecimal getRegularPrice() {
		return regularPrice.getAmount();
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice.getAmount();
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public BigDecimal getSalePrice() {
		return salePrice.getAmount();
	}

	public BigDecimal getDirectDiscountPrice() {
		return directDiscountPrice.getAmount();
	}

	public int getDirectDiscountRate() {
		return directDiscountRate;
	}

	public BigDecimal getRegularPriceAmount() {
		return regularPrice.getAmount();
	}
	public BigDecimal getCurrentPriceAmount() {
		return currentPrice.getAmount();
	}

	public BigDecimal getSalePriceAmount() {
		return salePrice.getAmount();
	}

	public BigDecimal getDirectDiscountPriceAmount() {
		return directDiscountPrice.getAmount();
	}


}
