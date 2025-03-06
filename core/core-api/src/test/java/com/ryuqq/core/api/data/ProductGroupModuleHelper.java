package com.ryuqq.core.api.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ryuqq.core.api.controller.v1.product.response.DefaultProductGroupContextResponseDto;
import com.ryuqq.core.domain.Money;
import com.ryuqq.core.domain.brand.DefaultBrand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.product.core.DefaultProductDelivery;
import com.ryuqq.core.domain.product.core.DefaultProductDetailDescription;
import com.ryuqq.core.domain.product.core.DefaultProductGroup;
import com.ryuqq.core.domain.product.core.DefaultProductGroupContext;
import com.ryuqq.core.domain.product.core.DefaultProductGroupImage;
import com.ryuqq.core.domain.product.core.DefaultProductGroupImageContext;
import com.ryuqq.core.domain.product.core.DefaultProductNotice;
import com.ryuqq.core.domain.seller.DefaultSeller;
import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.Origin;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductImageType;
import com.ryuqq.core.enums.ProductStatus;
import com.ryuqq.core.enums.ReturnMethod;
import com.ryuqq.core.enums.ShipmentCompanyCode;

public class ProductGroupModuleHelper {

	public static DefaultProductGroupContextResponseDto createDefaultProductGroupContextResponseDto(DefaultProductGroupContext productGroupContext){
		DefaultBrand brand = new DefaultBrand(4856L, "Moncler", "몽클레어", true);
		DefaultSeller seller = new DefaultSeller(28L, "setof");
		List<Category> categories = CategoryModuleHelper.createCategories();
		return new DefaultProductGroupContextResponseDto(productGroupContext, brand, categories, seller);
	}


	// SINGLE 타입 상품 그룹
	public static DefaultProductGroupContext createSingleProductGroup() {
		return DefaultProductGroupContext.builder()
			.productGroup(createProductGroup(1L, OptionType.SINGLE))
			.productDelivery(createDefaultProductDelivery(1L))
			.productNotice(createDefaultProductNotice(1L))
			.productDetailDescription(createDefaultProductDetailDescription(1L))
			.productGroupImages(createDefaultProductGroupImageContext(1L))
			.products(ProductModuleHelper.createProductOptionContext(1L, OptionType.SINGLE))
			.build();
	}

	// OPTION_ONE 타입 상품 그룹
	public static DefaultProductGroupContext createOptionOneProductGroup() {
		return DefaultProductGroupContext.builder()
			.productGroup(createProductGroup(2L, OptionType.OPTION_ONE))
			.productDelivery(createDefaultProductDelivery(2L))
			.productNotice(createDefaultProductNotice(2L))
			.productDetailDescription(createDefaultProductDetailDescription(2L))
			.productGroupImages(createDefaultProductGroupImageContext(2L))
			.products(ProductModuleHelper.createProductOptionContext(2L, OptionType.OPTION_ONE))
			.build();
	}

	// OPTION_TWO 타입 상품 그룹
	public static DefaultProductGroupContext createOptionTwoProductGroup() {
		return DefaultProductGroupContext.builder()
			.productGroup(createProductGroup(3L, OptionType.OPTION_TWO))
			.productDelivery(createDefaultProductDelivery(3L))
			.productNotice(createDefaultProductNotice(3L))
			.productDetailDescription(createDefaultProductDetailDescription(3L))
			.productGroupImages(createDefaultProductGroupImageContext(3L))
			.products(ProductModuleHelper.createProductOptionContext(3L, OptionType.OPTION_TWO))
			.build();
	}

	private static DefaultProductGroup createProductGroup(long productGroupId, OptionType optionType) {
		return DefaultProductGroup.create(
			productGroupId,
			28L,
			1549L,
			4856L,
			"몽클레어 키즈 와펜 패치 배색 폴로 반팔(네이비) 8A00002 89ALK 778",
			"STYLE_CODE-40120948",
			ProductCondition.NEW,
			ManagementType.SABANG,
			optionType,
			BigDecimal.valueOf(513000L),
			BigDecimal.valueOf(281000L),
			BigDecimal.valueOf(281000L),
			false,
			true,
			ProductStatus.WAITING,
			"",
			LocalDateTime.now(),
			LocalDateTime.now()
		);
	}

	public static DefaultProductDelivery createDefaultProductDelivery(long productGroupId) {
		return DefaultProductDelivery.create(
			productGroupId,
			"마포구 독막로 15 1층",
			Money.ZERO,
			0,
			ReturnMethod.RETURN_CONSUMER,
			ShipmentCompanyCode.SHIP04,
			Money.wons(10000),
			"마포구 독막로 15 1층"
		);
	}

	public static DefaultProductNotice createDefaultProductNotice(long productGroupId) {
		return DefaultProductNotice.create(
			productGroupId,
			"상세페이지 참조",
			"상세페이지 참조",
			"상세페이지 참조",
			"상세페이지 참조",
			Origin.KR,
			"상세페이지 참조",
			"상세페이지 참조",
			"상세페이지 참조",
			"070-1111-1111"
		);
	}

	public static DefaultProductDetailDescription createDefaultProductDetailDescription(long productGroupId) {
		return DefaultProductDetailDescription.create(
			productGroupId,
			"html Detail Description"
		);
	}

	public static DefaultProductGroupImageContext createDefaultProductGroupImageContext(long productGroupId) {
		return new DefaultProductGroupImageContext(
			productGroupId,
			List.of(
				DefaultProductGroupImage.create(1, productGroupId, ProductImageType.MAIN, "test1", "test1", 1, false),
				DefaultProductGroupImage.create(2, productGroupId, ProductImageType.DETAIL, "test2", "test2", 2, false)
			)
		);
	}

}
