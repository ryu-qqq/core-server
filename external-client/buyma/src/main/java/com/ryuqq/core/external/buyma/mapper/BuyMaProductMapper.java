package com.ryuqq.core.external.buyma.mapper;


import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.Price;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.external.buyma.BuyMaOptionContext;
import com.ryuqq.core.external.buyma.BuyMaPrice;
import com.ryuqq.core.external.buyma.helper.BuyMaImageInsertFactory;
import com.ryuqq.core.external.buyma.helper.BuyMaPriceHelper;
import com.ryuqq.core.external.buyma.helper.BuyMaProductInsertFactory;
import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestDto;

@Component
public class BuyMaProductMapper {

	private final BrandQueryInterface brandQueryInterface;
	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;
	private final BuyMaOptionMapper buyMaOptionMapper;

	public BuyMaProductMapper(BrandQueryInterface brandQueryInterface, ProductGroupContextQueryInterface productGroupContextQueryInterface,
							  BuyMaOptionMapper buyMaOptionMapper) {
		this.brandQueryInterface = brandQueryInterface;
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
		this.buyMaOptionMapper = buyMaOptionMapper;
	}

	public BuyMaProductInsertRequestDto toInsetRequestDto(ExternalProductGroup externalProductGroup){
		ProductGroupContext productGroupContext = productGroupContextQueryInterface.fetchById(externalProductGroup.getProductGroupId());

		ProductGroup productGroup = productGroupContext.getProductGroup();
		Price price = productGroup.getPrice();

		BuyMaOptionContext buyMaOptionContext = buyMaOptionMapper.toBuyMaOptionContext(externalProductGroup.getSiteId(),
			externalProductGroup.getProductGroupId(),
			externalProductGroup.getExternalCategoryId());

		BuyMaPrice buyMaPrice = BuyMaPriceHelper.calculateFinalPrice(price.getCurrentPrice(), BigDecimal.valueOf(933));

		Brand brand = brandQueryInterface.fetchById(externalProductGroup.getBrandId());

		return BuyMaProductInsertFactory.createInsertRequestDto(
			externalProductGroup,
			externalProductGroup.getProductName(),
			productGroup.getStyleCode(),
			brand.getBrandName(),
			Long.parseLong(externalProductGroup.getExternalBrandId()),
			Long.parseLong(externalProductGroup.getExternalCategoryId()),
			buyMaOptionContext.buyMaVariants(),
			buyMaOptionContext.buyMaOptions(),
			buyMaPrice.getCurrentPrice(),
			buyMaPrice.getRegularPrice(),
			BuyMaImageInsertFactory.toBuyMaImages(productGroupContext.getProductGroupImageContext()),
			buyMaOptionContext.optionComment()
		);

	}


}
