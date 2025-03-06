package com.ryuqq.core.external.oco.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.ExternalProductRequestMapper;
import com.ryuqq.core.domain.product.core.ProductDelivery;
import com.ryuqq.core.domain.product.core.ProductDetailDescription;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.domain.product.core.ProductNotice;
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
public class OcoProductMapper implements ExternalProductRequestMapper<OcoProductGroupInsertRequestContext, OcoProductGroupUpdateRequestContext> {

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

	@Override
	public OcoProductGroupInsertRequestContext toInsertRequestDto(ExternalProductGroup externalProductGroup) {
		CommonContext commonContext = prepareCommonContext(externalProductGroup);

		OcoProductInsertRequestDto insertRequestDto = OcoProductInsertFactory.createInsertRequestDto(
			externalProductGroup, commonContext.productGroup, commonContext.productNotice, commonContext.productDelivery,
			commonContext.price, commonContext.images, commonContext.productDetailDescription,
			commonContext.optionContexts, commonContext.externalCategoryId
		);

		return new OcoProductGroupInsertRequestContext(insertRequestDto, commonContext.optionContexts);
	}

	@Override
	public OcoProductGroupUpdateRequestContext toUpdateRequestDto(ExternalProductGroup externalProductGroup) {
		CommonContext commonContext = prepareCommonContext(externalProductGroup);

		OcoOptionContextHelper.PartitionedOcoOptionContexts partitionedContexts =
			OcoOptionContextHelper.partitionContexts(commonContext.optionContexts);


		OcoProductInsertRequestDto updateRequestDto = OcoProductInsertFactory.createInsertRequestDto(
			externalProductGroup, commonContext.productGroup, commonContext.productNotice, commonContext.productDelivery,
			commonContext.price, commonContext.images, commonContext.productDetailDescription,
			partitionedContexts.getInserts(), commonContext.externalCategoryId
		);

		return new OcoProductGroupUpdateRequestContext(updateRequestDto, partitionedContexts.getInserts(), partitionedContexts.getDeleted());
	}


	private CommonContext prepareCommonContext(ExternalProductGroup externalProductGroup) {
		ProductGroupContext productGroupContext = productGroupContextQueryInterface.fetchById(externalProductGroup.getProductGroupId());
		ProductGroup productGroup = productGroupContext.getProductGroup();
		ProductNotice productNotice = productGroupContext.getProductNotice();
		ProductDelivery productDelivery = productGroupContext.getProductDelivery();
		OcoPrice price = OcoPriceHelper.calculateFinalPrice(productGroup.getPrice().getRegularPrice(), productGroup.getPrice().getCurrentPrice());
		List<OcoImageInsertRequestDto> images = OcoImageInsertFactory.toOcoImages(productGroupContext.getProductGroupImageContext());
		List<OcoOptionContext> optionContexts = ocoOptionConverter.generateOptionContext(
			externalProductGroup.getProductGroupId(), externalProductGroup.getExternalProductGroupId());
		String externalCategoryId = resolveCategoryId(externalProductGroup);

		return new CommonContext(productGroup, productNotice, productDelivery, price, images,
			productGroupContext.getProductDetailDescription(), optionContexts, externalCategoryId);
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


	private record CommonContext(ProductGroup productGroup, ProductNotice productNotice, ProductDelivery productDelivery, OcoPrice price,
								 List<OcoImageInsertRequestDto> images, ProductDetailDescription productDetailDescription,
								 List<OcoOptionContext> optionContexts, String externalCategoryId) {
	}



}
