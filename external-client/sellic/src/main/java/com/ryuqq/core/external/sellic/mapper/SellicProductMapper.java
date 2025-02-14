package com.ryuqq.core.external.sellic.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.Price;
import com.ryuqq.core.domain.product.core.ProductDetailDescription;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
import com.ryuqq.core.domain.product.core.ProductNotice;
import com.ryuqq.core.external.sellic.SellicImage;
import com.ryuqq.core.external.sellic.SellicOptionContext;
import com.ryuqq.core.external.sellic.SellicOrigin;
import com.ryuqq.core.external.sellic.SellicPrice;
import com.ryuqq.core.external.sellic.helper.SellicImageInsertFactory;
import com.ryuqq.core.external.sellic.helper.SellicPriceHelper;
import com.ryuqq.core.external.sellic.request.SellicProductInsertRequestDto;

@Component
public class SellicProductMapper {

	private final BrandQueryInterface brandQueryInterface;
	private final ProductGroupContextQueryInterface productGroupContextQueryInterface;
	private final SellicOptionConverter sellicOptionConverter;
	private final SellicCategoryConverter sellicCategoryConverter;

	public SellicProductMapper(
		BrandQueryInterface brandQueryInterface,
		ProductGroupContextQueryInterface productGroupContextQueryInterface,
		SellicOptionConverter sellicOptionConverter,
		SellicCategoryConverter sellicCategoryConverter
	) {
		this.brandQueryInterface = brandQueryInterface;
		this.productGroupContextQueryInterface = productGroupContextQueryInterface;
		this.sellicOptionConverter = sellicOptionConverter;
		this.sellicCategoryConverter = sellicCategoryConverter;
	}

	public SellicProductInsertRequestDto toRequestDto(ExternalProductGroup externalProductGroup) {
		ProductGroupContext productGroupContext = productGroupContextQueryInterface.fetchById(
			externalProductGroup.getProductGroupId());

		Brand brand = brandQueryInterface.fetchById(productGroupContext.getProductGroup().getBrandId());

		ProductGroup productGroup = productGroupContext.getProductGroup();
		ProductNotice productNotice = productGroupContext.getProductNotice();
		Price price = productGroup.getPrice();

		List<SellicImage> sellicImages = SellicImageInsertFactory.toSellicImages(productGroupContext.getProductGroupImageContext());
		SellicPrice sellicPrice = SellicPriceHelper.calculateFinalPrice(price.getRegularPrice(), price.getCurrentPrice());

		SellicOptionContext sellicOptionContext = sellicOptionConverter.generateOptionContext(externalProductGroup.getProductGroupId());
		String externalCategoryId = resolveCategoryId(externalProductGroup);

		return buildRequestDto(
			productGroup, productNotice, sellicPrice, sellicImages, productGroupContext.getProductDetailDescription(), sellicOptionContext, externalCategoryId, brand, externalProductGroup
		);
	}


	private String resolveCategoryId(ExternalProductGroup externalProductGroup) {
		if (externalProductGroup.getExternalCategoryId() != null) {
			return externalProductGroup.getExternalCategoryId();
		}
		return sellicCategoryConverter.fetchExternalCategoryId(
			externalProductGroup.getSiteId(),
			externalProductGroup.getCategoryId()
		);
	}


	private SellicProductInsertRequestDto buildRequestDto(
		ProductGroup productGroup, ProductNotice productNotice, SellicPrice sellicPrice, List<SellicImage> sellicImages, ProductDetailDescription productDetailDescription,
		SellicOptionContext sellicOptionContext, String externalCategoryId, Brand brand, ExternalProductGroup externalProductGroup
	) {
		return new SellicProductInsertRequestDto.Builder()
			.productName(productGroup.getProductGroupName())
			.ownCode(String.valueOf(productGroup.getId()))
			.origin(SellicOrigin.of(productNotice.getOrigin().getDisplayName()))
			.categoryId(Integer.parseInt(externalCategoryId))
			.supplierName(externalProductGroup.getSellerName())
			.saleStatus(productGroup.isSoldOut() ? 2002 : 2000)
			.deliveryChargeType(1296)
			.deliveryFee("0")
			.tax(0)
			.brand(brand.getBrandName())
			.detailNote(productDetailDescription.getDetailDescription())
			.marketPrice(sellicPrice.regularPrice())
			.salePrice(sellicPrice.currentPrice())
			.image1(getImageOrDefault(sellicImages, 0))
			.image2(getImageOrDefault(sellicImages, 1))
			.image3(getImageOrDefault(sellicImages, 2))
			.image4(getImageOrDefault(sellicImages, 3))
			.image5(getImageOrDefault(sellicImages, 4))
			.image6(getImageOrDefault(sellicImages, 5))
			.image7(getImageOrDefault(sellicImages, 6))
			.notifyCode("1")
			.notify1(productNotice.getMaterial())
			.notify2(productNotice.getColor())
			.notify3(productNotice.getSize())
			.notify4(productNotice.getMaker())
			.notify5(productNotice.getOrigin().getDisplayName())
			.notify6(productNotice.getWashingMethod())
			.notify7(productNotice.getYearMonth())
			.notify8(productNotice.getAssuranceStandard())
			.notify9(productNotice.getAsPhone())
			.optionName1(sellicOptionContext.optionName1())
			.optionName2(sellicOptionContext.optionName2())
			.options(sellicOptionContext.options())
			.productId(externalProductGroup.getExternalProductGroupId() != null ?
				Long.parseLong(externalProductGroup.getExternalProductGroupId()) : null)
			.build();
	}

	private String getImageOrDefault(List<SellicImage> sellicImages, int index) {
		return sellicImages.size() > index ? sellicImages.get(index).imageUrl() : "";
	}

}
