package com.ryuqq.core.external.sellic.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.product.core.Item;
import com.ryuqq.core.domain.product.core.ItemContext;
import com.ryuqq.core.domain.product.core.ItemImage;
import com.ryuqq.core.domain.product.core.ItemNoticeInfo;
import com.ryuqq.core.domain.product.core.Price;
import com.ryuqq.core.domain.product.core.ProductGroupContextQueryInterface;
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
		ItemContext itemContext = productGroupContextQueryInterface.fetchByProductGroupId(externalProductGroup.getProductGroupId());
		Brand brand = brandQueryInterface.fetchById(externalProductGroup.getBrandId());
		Item item = itemContext.getItem();
		ItemNoticeInfo noticeInfo = itemContext.getNoticeInfo();
		Price price = item.getPrice();
		List<? extends ItemImage> itemImages = itemContext.getItemImages();

		SellicPrice sellicPrice = SellicPriceHelper.calculateFinalPrice(price.getRegularPrice(), price.getCurrentPrice());
		List<SellicImage> sellicImages = SellicImageInsertFactory.toSellicImages(itemImages);
		SellicOptionContext sellicOptionContext = sellicOptionConverter.generateOptionContext(externalProductGroup.getProductGroupId());
		String externalCategoryId = resolveCategoryId(externalProductGroup);

		return buildRequestDto(
			item, noticeInfo, sellicPrice, sellicImages, itemContext.getItemDescription(), sellicOptionContext, externalCategoryId, brand, externalProductGroup
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
		Item item, ItemNoticeInfo noticeInfo, SellicPrice sellicPrice, List<SellicImage> sellicImages, String detailDescription,
		SellicOptionContext sellicOptionContext, String externalCategoryId, Brand brand, ExternalProductGroup externalProductGroup
	) {
		return new SellicProductInsertRequestDto.Builder()
			.productName(item.getProductGroupName())
			.ownCode(String.valueOf(item.getId()))
			.origin(SellicOrigin.of(noticeInfo.getOrigin().getDisplayName()))
			.categoryId(Integer.parseInt(externalCategoryId))
			.supplierName(externalProductGroup.getSellerName())
			.saleStatus(item.isSoldOut() ? 2002 : 2000)
			.deliveryChargeType(1296)
			.deliveryFee("0")
			.tax(0)
			.brand(brand.getBrandName())
			.detailNote(detailDescription)
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
			.notify1(noticeInfo.getMaterial())
			.notify2(noticeInfo.getColor())
			.notify3(noticeInfo.getSize())
			.notify4(noticeInfo.getMaker())
			.notify5(noticeInfo.getOrigin().getDisplayName())
			.notify6(noticeInfo.getWashingMethod())
			.notify7(noticeInfo.getYearMonth())
			.notify8(noticeInfo.getAssuranceStandard())
			.notify9(noticeInfo.getAsPhone())
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
