package com.ryuqq.core.external.oco.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.Item;
import com.ryuqq.core.domain.product.core.ItemContext;
import com.ryuqq.core.domain.product.core.ItemDeliveryInfo;
import com.ryuqq.core.domain.product.core.ItemNoticeInfo;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.OcoPrice;
import com.ryuqq.core.external.oco.OcoProductGroupInsertRequestContext;
import com.ryuqq.core.external.oco.OcoProductGroupUpdateRequestContext;
import com.ryuqq.core.external.oco.helper.OcoImageInsertFactory;
import com.ryuqq.core.external.oco.helper.OcoOptionContextHelper;
import com.ryuqq.core.external.oco.helper.OcoPriceHelper;
import com.ryuqq.core.external.oco.helper.OcoProductInsertFactory;
import com.ryuqq.core.external.oco.request.OcoImageInsertRequestDto;
import com.ryuqq.core.external.oco.request.OcoProductInsertRequestDto;

@Component
public class OcoProductMapper {

	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;
	private final OcoCategoryConverter ocoCategoryConverter;
	private final OcoOptionConverter ocoOptionConverter;

	public OcoProductMapper(ProductGroupContextQueryInterface productGroupContextQueryInterface,
							OcoCategoryConverter ocoCategoryConverter,
							OcoOptionConverter ocoOptionConverter) {
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
		this.ocoCategoryConverter = ocoCategoryConverter;
		this.ocoOptionConverter = ocoOptionConverter;
	}

	public OcoProductGroupInsertRequestContext toInsetRequestDto(ExternalProductGroup externalProductGroup) {
		CommonContext commonContext = prepareCommonContext(externalProductGroup);

		OcoProductInsertRequestDto insertRequestDto = OcoProductInsertFactory.createInsertRequestDto(
			externalProductGroup, commonContext.item, commonContext.noticeInfo, commonContext.deliveryInfo,
			commonContext.price, commonContext.images, commonContext.itemDescription,
			commonContext.optionContexts, commonContext.externalCategoryId
		);

		return new OcoProductGroupInsertRequestContext(insertRequestDto, commonContext.optionContexts);
	}

	public OcoProductGroupUpdateRequestContext toUpdateRequestDto(ExternalProductGroup externalProductGroup) {
		CommonContext commonContext = prepareCommonContext(externalProductGroup);

		OcoOptionContextHelper.PartitionedOcoOptionContexts partitionedContexts =
			OcoOptionContextHelper.partitionContexts(commonContext.optionContexts);


		OcoProductInsertRequestDto updateRequestDto = OcoProductInsertFactory.createInsertRequestDto(
			externalProductGroup, commonContext.item, commonContext.noticeInfo, commonContext.deliveryInfo,
			commonContext.price, commonContext.images, commonContext.itemDescription,
			partitionedContexts.getInserts(), commonContext.externalCategoryId
		);

		return new OcoProductGroupUpdateRequestContext(updateRequestDto, partitionedContexts.getInserts(), partitionedContexts.getDeleted());
	}


	private CommonContext prepareCommonContext(ExternalProductGroup externalProductGroup) {
		ItemContext itemContext = productGroupContextQueryInterface.fetchByProductGroupId(externalProductGroup.getProductGroupId());
		Item item = itemContext.getItem();
		ItemNoticeInfo noticeInfo = itemContext.getNoticeInfo();
		ItemDeliveryInfo deliveryInfo = itemContext.getDeliveryInfo();
		OcoPrice price = OcoPriceHelper.calculateFinalPrice(item.getPrice().getRegularPrice(), item.getPrice().getCurrentPrice());
		List<OcoImageInsertRequestDto> images = OcoImageInsertFactory.toOcoImages(itemContext.getItemImages());
		List<OcoOptionContext> optionContexts = ocoOptionConverter.generateOptionContext(
			externalProductGroup.getProductGroupId(), externalProductGroup.getExternalProductGroupId());
		String externalCategoryId = resolveCategoryId(externalProductGroup);

		return new CommonContext(item, noticeInfo, deliveryInfo, price, images,
			itemContext.getItemDescription(), optionContexts, externalCategoryId);
	}

	private String resolveCategoryId(ExternalProductGroup externalProductGroup) {
		if (externalProductGroup.getExternalCategoryId() != null) {
			return externalProductGroup.getExternalCategoryId();
		}
		return ocoCategoryConverter.fetchExternalCategoryId(
			externalProductGroup.getSiteId(),
			externalProductGroup.getCategoryId()
		);
	}


	private record CommonContext(Item item, ItemNoticeInfo noticeInfo, ItemDeliveryInfo deliveryInfo, OcoPrice price,
								 List<OcoImageInsertRequestDto> images, String itemDescription,
								 List<OcoOptionContext> optionContexts, String externalCategoryId) {
	}



}
