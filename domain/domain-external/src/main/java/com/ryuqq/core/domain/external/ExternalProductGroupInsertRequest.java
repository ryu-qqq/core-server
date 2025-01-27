package com.ryuqq.core.domain.external;

import com.ryuqq.core.domain.Money;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;

public record ExternalProductGroupInsertRequest(
	long productGroupId,
	String productName,
	String styleCode,
	ProductCondition productCondition,
	OptionType optionType,
	Money regularPrice,
	Money currentPrice,
	boolean soldOut,
	boolean displayed

) {}
