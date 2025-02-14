package com.ryuqq.core.storage.db.product.group;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.DefaultProductDelivery;
import com.ryuqq.core.domain.product.core.DefaultProductDetailDescription;
import com.ryuqq.core.domain.product.core.DefaultProductGroup;
import com.ryuqq.core.domain.product.core.DefaultProductGroupContext;
import com.ryuqq.core.domain.product.core.DefaultProductGroupImageContext;
import com.ryuqq.core.domain.product.core.DefaultProductNotice;
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

	public DefaultProductGroupContext toDomain(ProductGroupContextDto dto){
		return DefaultProductGroupContext.builder()
			.productGroup(toProductGroupDomain(dto.getProductGroupDto()))
			.productNotice(toProductNotice(dto.getProductNoticeDto()))
			.productDelivery(toProductDelivery(dto.getProductDeliveryDto()))
			.productGroupImages(toProductGroupImages(dto.getProductGroupId(), dto.getProductGroupImageDto()))
			.productDetailDescription(toProductDetailDescription(dto.getProductDetailDescriptionDto()))
			.build();
	}

	public DefaultProductGroup toProductGroupDomain(ProductGroupDto dto){
		return ProductGroupDomainMapper.toDomain(dto);
	}

	private DefaultProductNotice toProductNotice(ProductNoticeDto dto) {
		return ProductDomainMapper.toDomain(dto);
	}

	private DefaultProductDelivery toProductDelivery(ProductDeliveryDto dto) {
			return ProductDeliveryDomainMapper.toDomain(dto);
	}

	private DefaultProductGroupImageContext toProductGroupImages(long productGroupId, List<ProductGroupImageDto> images) {
		return new DefaultProductGroupImageContext(productGroupId, ProductImageDomainMapper.toProductGroupImages(images));
	}

	private DefaultProductDetailDescription toProductDetailDescription(ProductDetailDescriptionDto dto) {
		return ProductDetailDescriptionDomainMapper.toProductDetailDescription(dto);
	}

}
