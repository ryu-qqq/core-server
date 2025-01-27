package com.ryuqq.core.storage.db.product.group;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.storage.db.product.delivery.ProductDeliveryDto;
import com.ryuqq.core.storage.db.product.image.ProductDetailDescriptionDto;
import com.ryuqq.core.storage.db.product.image.ProductGroupImageDto;
import com.ryuqq.core.storage.db.product.notice.ProductNoticeDto;

public class ProductGroupContextDto {
	private final ProductGroupDto productGroupDto;
	private final ProductNoticeDto productNoticeDto;
	private final ProductDeliveryDto productDeliveryDto;
	private final List<ProductGroupImageDto> productGroupImageDto;
	private final ProductDetailDescriptionDto productDetailDescriptionDto;


	@QueryProjection
	public ProductGroupContextDto(ProductGroupDto productGroupDto, ProductNoticeDto productNoticeDto,
								  ProductDeliveryDto productDeliveryDto,
								  List<ProductGroupImageDto> productGroupImageDto,
								  ProductDetailDescriptionDto productDetailDescriptionDto) {
		this.productGroupDto = productGroupDto;
		this.productNoticeDto = productNoticeDto;
		this.productDeliveryDto = productDeliveryDto;
		this.productGroupImageDto = productGroupImageDto;
		this.productDetailDescriptionDto = productDetailDescriptionDto;
	}

	public long getProductGroupId(){
		return productGroupDto.getProductGroupId();
	}

	public ProductGroupDto getProductGroupDto() {
		return productGroupDto;
	}

	public ProductNoticeDto getProductNoticeDto() {
		return productNoticeDto;
	}

	public ProductDeliveryDto getProductDeliveryDto() {
		return productDeliveryDto;
	}

	public List<ProductGroupImageDto> getProductGroupImageDto() {
		return productGroupImageDto;
	}

	public ProductDetailDescriptionDto getProductDetailDescriptionDto() {
		return productDetailDescriptionDto;
	}
}
