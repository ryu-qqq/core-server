package com.ryuqq.core.external.buyma.helper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.external.buyma.request.BuyMaImageInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaOptionInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaShippingMethodDto;
import com.ryuqq.core.external.buyma.request.BuyMaVariantInsertRequestDto;

public class BuyMaProductInsertFactory {

	public static BuyMaProductInsertRequestDto createInsertRequestDto(
		ExternalProductGroup externalProductGroup,
		String productGroupName,
		String styleCode,
		String brandName,
		long brandId,
		long categoryId,
		List<BuyMaVariantInsertRequestDto> variants,
		List<BuyMaOptionInsertRequestDto> options,
		BigDecimal buyMaPrice,
		BigDecimal buyMaReferencePrice,
		List<BuyMaImageInsertRequestDto> images,
		String colorSizeComment
	) {
		return new BuyMaProductInsertRequestDto.Builder()
			.buyingAreaId(2002003001)
			.shippingAreaId(2002003001)
			.availableUntil(LocalDateTime.now().plusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
			.duty("included")
			.control("publish")
			.shippingMethods(DEFAULT_SHIPPING_METHODS())
			.price(buyMaPrice.intValue())
			.referencePrice(buyMaReferencePrice.intValue())
			.images(images)
			.id(externalProductGroup.getExternalProductGroupId() == null ? null : Long.parseLong(externalProductGroup.getExternalProductGroupId()))
			.options(options)
			.variants(variants)
			.colorSizeComment(colorSizeComment)
			.name(productGroupName)
			.comments(productGroupName)
			.brandId(brandId)
			.brandName(brandName)
			.categoryId(categoryId)
			.referenceNumber(BuyMaReferenceNumberHelper.getDefaultReferenceNumber(styleCode, externalProductGroup.getProductGroupId()))
			.build();
	}

	private static List<BuyMaShippingMethodDto> DEFAULT_SHIPPING_METHODS(){
		return List.of(
			new BuyMaShippingMethodDto(984481),
			new BuyMaShippingMethodDto(984491)
		);
	}

}
