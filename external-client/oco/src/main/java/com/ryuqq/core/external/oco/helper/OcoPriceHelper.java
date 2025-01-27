package com.ryuqq.core.external.oco.helper;

import java.math.BigDecimal;

import com.ryuqq.core.domain.Money;
import com.ryuqq.core.external.oco.OcoPrice;


public class OcoPriceHelper {

    public static OcoPrice calculateFinalPrice(BigDecimal regularPrice, BigDecimal currentPrice) {
		Money regularMoney = Money.wons(regularPrice);
		Money currentMoney = Money.wons(currentPrice);

		Money newCurrentPrice = currentMoney.times(1.25);
		Money newRegularPrice = regularMoney.isGreaterThan(currentMoney) ? regularMoney : newCurrentPrice;
        return new OcoPrice(newRegularPrice.getAmount(), newCurrentPrice.getAmount());
    }

}
