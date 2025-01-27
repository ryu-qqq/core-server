package com.ryuqq.core.external.oco;

import java.math.BigDecimal;

public record OcoPrice(
	BigDecimal regularPrice,
	BigDecimal currentPrice
) {}
