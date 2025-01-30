package com.ryuqq.core.external.buyma.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.Item;
import com.ryuqq.core.domain.product.core.ItemContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.external.buyma.BuyMaOptionContext;
import com.ryuqq.core.external.buyma.helper.BuyMaReferenceNumberHelper;
import com.ryuqq.core.external.buyma.request.BuyMaProductStockUpdateRequestDto;

@Component
public class BuyMaStockMapper {

	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;
	private final BuyMaOptionMapper buyMaOptionMapper;

	public BuyMaStockMapper(ProductGroupContextQueryInterface productGroupContextQueryInterface, BuyMaOptionMapper buyMaOptionMapper) {
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
		this.buyMaOptionMapper = buyMaOptionMapper;
	}

	public BuyMaProductStockUpdateRequestDto toBuyMaProductStockUpdateRequestDto(
		ExternalProductGroup externalProductGroup) {


		ItemContext itemContext = productGroupContextQueryInterface.fetchByProductGroupId(
			externalProductGroup.getProductGroupId());

		Item item = itemContext.getItem();

		BuyMaOptionContext buyMaOptionContext = buyMaOptionMapper.toBuyMaOptionContext(externalProductGroup.getSiteId(),
			externalProductGroup.getProductGroupId(), externalProductGroup.getExternalCategoryId());

		return new BuyMaProductStockUpdateRequestDto(
			BuyMaReferenceNumberHelper.getDefaultReferenceNumber(item.getStyleCode(), item.getId()),
			buyMaOptionContext.buyMaVariants()
		);

	}
}
