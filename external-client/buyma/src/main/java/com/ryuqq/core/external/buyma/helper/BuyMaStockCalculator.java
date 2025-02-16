package com.ryuqq.core.external.buyma.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.ryuqq.core.domain.product.core.OptionContext;
import com.ryuqq.core.domain.product.core.ProductContext;



public class BuyMaStockCalculator {

	public static int calculateTotalQuantity(List<? extends ProductContext> productContexts) {
		Integer totalQuantity = productContexts.stream().map(p -> p.getProduct().getQuantity()).reduce(0, Integer::sum);
		return Math.min(totalQuantity, 999);
	}

	public static String determineStockType(List<? extends ProductContext> productContexts) {
		return calculateTotalQuantity(productContexts) > 0 ? "stock_in_hand" : "out_of_stock";
	}

	public static String determineStockType(int totalStock) {
		return totalStock > 0 ? "stock_in_hand" : "out_of_stock";
	}

	public static String getOptionComment(List<? extends ProductContext> productContexts) {
		return productContexts.stream()
			.map(productContext -> productContext.getOptions().stream()
				.map(OptionContext::getOptionValue)
				.collect(Collectors.joining(" ")))
			.collect(Collectors.joining("\n")) + "\nFor more information, please contact us";
	}
}
