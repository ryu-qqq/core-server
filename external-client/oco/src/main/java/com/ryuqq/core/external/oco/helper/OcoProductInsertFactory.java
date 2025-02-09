package com.ryuqq.core.external.oco.helper;

import java.util.List;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.ProductDelivery;
import com.ryuqq.core.domain.product.core.ProductDetailDescription;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductNotice;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.OcoPrice;
import com.ryuqq.core.external.oco.request.OcoImageInsertRequestDto;
import com.ryuqq.core.external.oco.request.OcoProductInsertRequestDto;

public class OcoProductInsertFactory {

	public static OcoProductInsertRequestDto createInsertRequestDto(
		ExternalProductGroup externalProductGroup,
		ProductGroup productGroup,
		ProductNotice productNotice,
		ProductDelivery productDelivery,
		OcoPrice price,
		List<OcoImageInsertRequestDto> images,
		ProductDetailDescription productDetailDescription,
		List<OcoOptionContext> optionContexts,
		String externalCategoryId
	) {
		return new OcoProductInsertRequestDto.Builder()
			.preAddYn("N")
			.pgid(Integer.parseInt(externalProductGroup.getExternalBrandId()))
			.pcid(Integer.parseInt(externalCategoryId))
			.productType("N")
			.name(productGroup.getProductGroupName())
			.madeIn(productNotice.getOrigin().getDisplayName())
			.manufacture(productNotice.getMaker())
			.infoMaterial(productNotice.getMaterial())
			.infoColor(productNotice.getColor())
			.infoAsTel(productNotice.getAsPhone())
			.infoQaTel(productNotice.getAsPhone())
			.infoAddr(productDelivery.getDeliveryArea())
			.code(String.valueOf(productGroup.getId()))
			.originPrice(price.regularPrice().intValue())
			.price(price.currentPrice().intValue())
			.salePriceYn("N")
			.saleTimeYn("N")
			.stock(getTotalStock(optionContexts))
			.keyword("")
			.soldOut(productGroup.isSoldOut() ? 1 : 0)
			.findYn(productGroup.isDisplayed() ? "Y" : "N")
			.useCouponYn("Y")
			.hidden(productGroup.isDisplayed() ? 0 : 1)
			.optionYn(productGroup.getOptionType().isMultiOption() ? "Y" : "N")
			.optionInputYn("N")
			.optionCount(getOptionLength(productGroup.getOptionType()))
			.deliveryPriceFreeYn("Y")
			.reDeliveryViewYn("N")
			.listImageFile(images.getFirst().relativePath())
			.fileList(images)
			.mainImagePath(images.getFirst().relativePath())
			.content(productDetailDescription.getDetailDescription())
			.optionList(optionContexts.stream()
				.map(OcoOptionContext::ocoOptionInsertRequestDto)
				.toList())
			.pid(externalProductGroup.getExternalProductGroupId() != null ?
				Integer.parseInt(externalProductGroup.getExternalProductGroupId()) :
				null)
			.build();
	}


	private static int getOptionLength(OptionType optionType) {
		return switch (optionType) {
			case SINGLE -> 0;
			case OPTION_ONE -> 1;
			default -> 2;
		};
	}

	private static int getTotalStock(List<OcoOptionContext> optionContexts) {
		return optionContexts.stream()
			.map(o -> o.ocoOptionInsertRequestDto().quantity())
			.reduce(0, Integer::sum);
	}



}
