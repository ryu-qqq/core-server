package com.ryuqq.core.external.buyma.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
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


		ProductGroupContext productGroupContext = productGroupContextQueryInterface.fetchById(
			externalProductGroup.getProductGroupId());

		ProductGroup productGroup = productGroupContext.getProductGroup();

		BuyMaOptionContext buyMaOptionContext = buyMaOptionMapper.toBuyMaOptionContext(externalProductGroup.getSiteId(),
			externalProductGroup.getProductGroupId(), externalProductGroup.getExternalCategoryId());

		return new BuyMaProductStockUpdateRequestDto(
			BuyMaReferenceNumberHelper.getDefaultReferenceNumber(productGroup.getStyleCode(), productGroup.getId()),
			buyMaOptionContext.buyMaVariants()
		);

	}
}
