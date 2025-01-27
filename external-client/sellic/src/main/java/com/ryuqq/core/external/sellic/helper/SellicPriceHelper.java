package com.ryuqq.core.external.sellic.helper;

import java.math.BigDecimal;

import com.ryuqq.core.domain.Money;
import com.ryuqq.core.external.sellic.SellicPrice;

public class SellicPriceHelper {

    public static SellicPrice calculateFinalPrice(BigDecimal regularPrice, BigDecimal currentPrice) {
		Money currentMoney = Money.wons(currentPrice);

		int newCurrentPrice = currentMoney.times(1.375).getAmount().intValue();
        int newRegularPrice = Math.max(regularPrice.intValueExact(), newCurrentPrice);
        return new SellicPrice(newRegularPrice, newRegularPrice);
    }

}
