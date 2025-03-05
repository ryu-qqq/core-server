package com.ryuqq.core.external.buyma.mapper;


import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.ExternalProductRequestMapper;
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
import com.ryuqq.core.external.openAi.TranslateResult;
import com.ryuqq.core.external.openAi.TranslationService;

@Component
public class BuyMaProductMapper implements ExternalProductRequestMapper<BuyMaProductInsertRequestDto, BuyMaProductInsertRequestDto> {

	private final TranslationService translationService;
	private final BrandQueryInterface brandQueryInterface;
	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;
	private final BuyMaOptionMapper buyMaOptionMapper;

	public BuyMaProductMapper(TranslationService translationService, BrandQueryInterface brandQueryInterface, ProductGroupContextQueryInterface productGroupContextQueryInterface,
							  BuyMaOptionMapper buyMaOptionMapper) {
		this.translationService = translationService;
		this.brandQueryInterface = brandQueryInterface;
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
		this.buyMaOptionMapper = buyMaOptionMapper;
	}

	@Override
	public BuyMaProductInsertRequestDto toInsertRequestDto(ExternalProductGroup externalProductGroup){
		ProductGroupContext productGroupContext = productGroupContextQueryInterface.fetchById(externalProductGroup.getProductGroupId());

		ProductGroup productGroup = productGroupContext.getProductGroup();
		Price price = productGroup.getPrice();

		BuyMaOptionContext buyMaOptionContext = buyMaOptionMapper.toBuyMaOptionContext(externalProductGroup.getSiteId(),
			externalProductGroup.getProductGroupId(),
			externalProductGroup.getExternalCategoryId());

		BuyMaPrice buyMaPrice = BuyMaPriceHelper.calculateFinalPrice(price.getCurrentPrice(), BigDecimal.valueOf(933));

		Brand brand = brandQueryInterface.fetchById(externalProductGroup.getBrandId());

		String productGroupName;
		if(externalProductGroup.getProductName() != null){
			productGroupName = externalProductGroup.getProductName();
		}else{
			TranslateResult translate = translationService.translate(productGroup.getProductGroupName());
			productGroupName = translate.translatedText();
		}


		return BuyMaProductInsertFactory.createInsertRequestDto(
			externalProductGroup,
			productGroupName,
			productGroup.getStyleCode(),
			brand.brandName(),
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

	@Override
	public BuyMaProductInsertRequestDto toUpdateRequestDto(ExternalProductGroup externalProductGroup) {
		return toInsertRequestDto(externalProductGroup);
	}

}
