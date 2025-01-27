package com.ryuqq.core.external.oco.helper;

import java.util.List;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.Item;
import com.ryuqq.core.domain.product.core.ItemDeliveryInfo;
import com.ryuqq.core.domain.product.core.ItemNoticeInfo;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.OcoPrice;
import com.ryuqq.core.external.oco.request.OcoImageInsertRequestDto;
import com.ryuqq.core.external.oco.request.OcoProductInsertRequestDto;

public class OcoProductInsertFactory {

	public static OcoProductInsertRequestDto createInsertRequestDto(
		ExternalProductGroup externalProductGroup,
		Item item,
		ItemNoticeInfo noticeInfo,
		ItemDeliveryInfo deliveryInfo,
		OcoPrice price,
		List<OcoImageInsertRequestDto> images,
		String detailDescription,
		List<OcoOptionContext> optionContexts,
		String externalCategoryId
	) {
		return new OcoProductInsertRequestDto.Builder()
			.preAddYn("N")
			.pgid(Integer.parseInt(externalProductGroup.getExternalBrandId()))
			.pcid(Integer.parseInt(externalCategoryId))
			.productType("N")
			.name(item.getProductGroupName())
			.madeIn(noticeInfo.getOrigin().getDisplayName())
			.manufacture(noticeInfo.getMaker())
			.infoMaterial(noticeInfo.getMaterial())
			.infoColor(noticeInfo.getColor())
			.infoAsTel(noticeInfo.getAsPhone())
			.infoQaTel(noticeInfo.getAsPhone())
			.infoAddr(deliveryInfo.getDeliveryArea())
			.code(String.valueOf(item.getId()))
			.originPrice(price.regularPrice().intValue())
			.price(price.currentPrice().intValue())
			.salePriceYn("N")
			.saleTimeYn("N")
			.stock(getTotalStock(optionContexts))
			.keyword("")
			.soldOut(item.isSoldOut() ? 1 : 0)
			.findYn(item.isDisplayed() ? "Y" : "N")
			.useCouponYn("Y")
			.hidden(item.isDisplayed() ? 0 : 1)
			.optionYn(item.getOptionType().isMultiOption() ? "Y" : "N")
			.optionInputYn("N")
			.optionCount(getOptionLength(item.getOptionType()))
			.deliveryPriceFreeYn("Y")
			.reDeliveryViewYn("N")
			.listImageFile(images.getFirst().relativePath())
			.fileList(images)
			.mainImagePath(images.getFirst().relativePath())
			.content(detailDescription)
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
