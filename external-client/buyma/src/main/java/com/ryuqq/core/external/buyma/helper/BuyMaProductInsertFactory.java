package com.ryuqq.core.external.buyma.helper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.external.buyma.request.BuyMaImageInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaOptionInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestDto;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestWrapperDto;
import com.ryuqq.core.external.buyma.request.BuyMaShippingMethodDto;
import com.ryuqq.core.external.buyma.request.BuyMaVariantInsertRequestDto;

public class BuyMaProductInsertFactory {

	public static BuyMaProductInsertRequestWrapperDto createInsertRequestDto(
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
		BuyMaProductInsertRequestDto buyMaProductInsertRequestDto = new BuyMaProductInsertRequestDto.Builder()
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
			.referenceNumber(DEFAULT_REFERENCE_NUMBER(styleCode, externalProductGroup.getProductGroupId()))
			.build();


		return new BuyMaProductInsertRequestWrapperDto(buyMaProductInsertRequestDto);
	}

	private static List<BuyMaShippingMethodDto> DEFAULT_SHIPPING_METHODS(){
		return List.of(
			new BuyMaShippingMethodDto(984481),
			new BuyMaShippingMethodDto(984491)
		);
	}

	private static String DEFAULT_REFERENCE_NUMBER(String styleCode, long productGroupId) {
		StringBuilder sb = new StringBuilder();
		sb.append(productGroupId);

		if (styleCode != null && !styleCode.isEmpty()) {
			sb.append("_");
			sb.append(styleCode);
		}
		return sb.toString();
	}

}
