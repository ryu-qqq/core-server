package com.ryuqq.core.api.data;

import java.math.BigDecimal;
import java.util.List;

import com.ryuqq.core.domain.product.core.DefaultOptionContext;
import com.ryuqq.core.domain.product.core.DefaultProduct;
import com.ryuqq.core.domain.product.core.DefaultProductContext;
import com.ryuqq.core.domain.product.core.DefaultProductOptionContext;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.enums.OptionType;

public class ProductModuleHelper {

	/**
	 * ProductOptionContext 생성 (1단, 2단, 싱글 옵션 대응)
	 */
	public static ProductOptionContext createProductOptionContext(Long productGroupId, OptionType optionType) {
		return switch (optionType) {
			case OPTION_ONE -> createSingleLevelOptionContext(productGroupId);
			case OPTION_TWO -> createTwoLevelOptionContext(productGroupId);
			case SINGLE -> createSingleOptionContext(productGroupId);
		};
	}

	/**
	 * ✅ 1단 옵션(예: 컬러만 있음) 생성
	 */
	private static ProductOptionContext createSingleLevelOptionContext(Long productGroupId) {
		List<DefaultProductContext> products = List.of(
			createProductContext(1L, false, List.of(createOptionContext(OptionName.COLOR, "Red"))),
			createProductContext(2L, false, List.of(createOptionContext(OptionName.COLOR, "Blue")))
		);
		return new DefaultProductOptionContext(productGroupId, OptionType.OPTION_ONE, products);
	}

	/**
	 * ✅ 2단 옵션(예: 컬러 + 사이즈) 생성
	 */
	private static ProductOptionContext createTwoLevelOptionContext(Long productGroupId) {
		List<DefaultProductContext> products = List.of(
			createProductContext(1L, false, List.of(
				createOptionContext(OptionName.COLOR, "Red"),
				createOptionContext(OptionName.SIZE, "M")
			)),
			createProductContext(2L, false, List.of(
				createOptionContext(OptionName.COLOR, "Red"),
				createOptionContext(OptionName.SIZE, "L")
			)),
			createProductContext(3L, false, List.of(
				createOptionContext(OptionName.COLOR, "Blue"),
				createOptionContext(OptionName.SIZE, "S")
			))
		);
		return new DefaultProductOptionContext(productGroupId, OptionType.OPTION_TWO, products);
	}

	/**
	 * ✅ 싱글 옵션(예: 단일 상품) 생성
	 */
	private static ProductOptionContext createSingleOptionContext(Long productGroupId) {
		List<DefaultProductContext> products = List.of(
			createProductContext(1L, false, List.of())
		);
		return new DefaultProductOptionContext(productGroupId, OptionType.SINGLE, products);
	}

	/**
	 * ✅ ProductContext 생성 (옵션 포함)
	 */
	public static DefaultProductContext createProductContext(Long productId, boolean isSoldOut, List<DefaultOptionContext> options) {
		return new DefaultProductContext(createProduct(productId, isSoldOut), options, false);
	}

	/**
	 * ✅ Product 생성
	 */
	public static DefaultProduct createProduct(Long id, boolean isSoldOut) {
		return new DefaultProduct(id, 1L, isSoldOut, true, 10, BigDecimal.valueOf(1000), false);
	}

	/**
	 * ✅ OptionContext 생성 (옵션 정보 포함)
	 */
	public static DefaultOptionContext createOptionContext(OptionName optionName, String optionValue) {
		return new DefaultOptionContext(1L, 1L, 1L, 1L, optionName, optionValue, false);
	}


}
