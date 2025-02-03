package com.ryuqq.core.external.buyma.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.ryuqq.core.domain.product.core.Sku;
import com.ryuqq.core.domain.product.core.Variant;

public class BuyMaStockCalculator {

	public static int calculateTotalQuantity(List<? extends Sku> skus) {
		return skus.stream().map(Sku::getQuantity).reduce(0, Integer::sum);
	}

	public static String determineStockType(List<? extends Sku> skus) {
		return calculateTotalQuantity(skus) > 0 ? "stock_in_hand" : "out_of_stock";
	}

	public static String determineStockType(int totalStock) {
		return totalStock > 0 ? "stock_in_hand" : "out_of_stock";
	}

	public static String getOptionComment(List<? extends Sku> skus) {
		return skus.stream()
			.map(sku -> sku.getVariants().stream()
				.map(Variant::getOptionValue)
				.collect(Collectors.joining(" ")))
			.collect(Collectors.joining("\n")) + "\nFor more information, please contact us";
	}
}
