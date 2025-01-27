package com.ryuqq.core.storage.db.product.group;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.ProductDelivery;
import com.ryuqq.core.domain.product.ProductDetailDescription;
import com.ryuqq.core.domain.product.ProductGroup;
import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.domain.product.ProductGroupImageBundle;
import com.ryuqq.core.domain.product.ProductNotice;
import com.ryuqq.core.storage.db.product.delivery.ProductDeliveryDomainMapper;
import com.ryuqq.core.storage.db.product.delivery.ProductDeliveryDto;
import com.ryuqq.core.storage.db.product.image.ProductDetailDescriptionDomainMapper;
import com.ryuqq.core.storage.db.product.image.ProductDetailDescriptionDto;
import com.ryuqq.core.storage.db.product.image.ProductGroupImageDto;
import com.ryuqq.core.storage.db.product.image.ProductImageDomainMapper;
import com.ryuqq.core.storage.db.product.notice.ProductDomainMapper;
import com.ryuqq.core.storage.db.product.notice.ProductNoticeDto;

@Component
public class ProductGroupContextDomainMapper {

	public ProductGroupContext toDomain(ProductGroupContextDto dto){
		return ProductGroupContext.builder()
			.productGroup(toProductGroupDomain(dto.getProductGroupDto()))
			.productNotice(toProductNotice(dto.getProductNoticeDto()))
			.productDelivery(toProductDelivery(dto.getProductDeliveryDto()))
			.productGroupImages(toProductGroupImages(dto.getProductGroupImageDto()))
			.productDetailDescription(toProductDetailDescription(dto.getProductDetailDescriptionDto()))
			.build();
	}

	public ProductGroup toProductGroupDomain(ProductGroupDto dto){
		return ProductGroupDomainMapper.toDomain(dto);
	}

	private ProductNotice toProductNotice(ProductNoticeDto dto) {
		return ProductDomainMapper.toDomain(dto);
	}

	private ProductDelivery toProductDelivery(ProductDeliveryDto dto) {
			return ProductDeliveryDomainMapper.toDomain(dto);
	}

	private ProductGroupImageBundle toProductGroupImages(List<ProductGroupImageDto> images) {
		return new ProductGroupImageBundle(ProductImageDomainMapper.toProductGroupImages(images));
	}

	private ProductDetailDescription toProductDetailDescription(ProductDetailDescriptionDto dto) {
		return ProductDetailDescriptionDomainMapper.toProductDetailDescription(dto);
	}

}
