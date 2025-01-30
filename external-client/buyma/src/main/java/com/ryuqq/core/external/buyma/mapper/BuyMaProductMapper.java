package com.ryuqq.core.external.buyma.mapper;


import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestWrapperDto;

@Component
public class BuyMaProductMapper {

	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;

	public BuyMaProductMapper(ProductGroupContextQueryInterface productGroupContextQueryInterface) {
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
	}

	public BuyMaProductInsertRequestWrapperDto toInsetRequestDto(ExternalProductGroup externalProductGroup){

		return null;
	}


	// private CommonContext prepareCommonContext(ExternalProductGroup externalProductGroup) {
	// 	ItemContext itemContext = productGroupContextQueryInterface.fetchByProductGroupId(externalProductGroup.getProductGroupId());
	// 	Item item = itemContext.getItem();
	// 	ItemNoticeInfo noticeInfo = itemContext.getNoticeInfo();
	// 	ItemDeliveryInfo deliveryInfo = itemContext.getDeliveryInfo();
	// 	BigDecimal buyMaPrice = BuyMaPriceHelper.calculateFinalPrice(item.getPrice().getRegularPrice(),
	// 		BigDecimal.valueOf(934.56));
	// 	List<BuyMaImageInsertRequestDto> buyMaImages = BuyMaImageInsertFactory.toBuyMaImages(
	// 		itemContext.getItemImages());
	// 	List<OcoOptionContext> optionContexts = ocoOptionConverter.generateOptionContext(
	// 		externalProductGroup.getProductGroupId(), externalProductGroup.getExternalProductGroupId());
	// 	String externalCategoryId = resolveCategoryId(externalProductGroup);
	//
	// 	return new CommonContext(item, noticeInfo, deliveryInfo, price, images,
	// 		itemContext.getItemDescription(), optionContexts, externalCategoryId);
	// }



}
