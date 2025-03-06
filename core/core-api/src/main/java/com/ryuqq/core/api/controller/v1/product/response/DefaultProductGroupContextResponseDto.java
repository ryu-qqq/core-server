package com.ryuqq.core.api.controller.v1.product.response;

import java.util.List;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.product.ProductGroupImageContext;
import com.ryuqq.core.domain.product.core.ProductDelivery;
import com.ryuqq.core.domain.product.core.ProductDetailDescription;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductNotice;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.domain.seller.core.Seller;

public class DefaultProductGroupContextResponseDto implements ProductGroupContextResponse {

	private final DefaultProductGroupResponseDto productGroup;
	private final ProductNoticeResponseDto productNotice;
	private final ProductDeliveryResponseDto productDelivery;
	private final ProductDetailDescriptionResponseDto productDetailDescription;
	private final List<ProductGroupImageResponseDto> productGroupImages;
	private final List<ProductResponseDto> products;
	private final ProductBrandResponseDto brand;
	private final List<ProductCategoryResponseDto> categories;

	public DefaultProductGroupContextResponseDto(ProductGroupContext productGroupContext, Brand brand,
										  List<? extends Category> categories, Seller seller){

		this.productGroup = toProductGroupResponseDto(productGroupContext.getProductGroup(), seller);
		this.productNotice = toProductNoticeResponseDto(productGroupContext.getProductNotice());
		this.productDelivery = toProductDeliveryResponseDto(productGroupContext.getProductDelivery());
		this.productDetailDescription = toProductDetailDescriptionResponseDto(productGroupContext.getProductDetailDescription());
		this.productGroupImages = toProductGroupImageResponseDto(productGroupContext.getProductGroupImageContext());
		this.products = toProducts(productGroupContext.getProductOptionContext());
		this.brand = toBrandResponseDto(brand);
		this.categories = toCategoryResponseDto(categories);
	}

	private DefaultProductGroupResponseDto toProductGroupResponseDto(ProductGroup productGroup, Seller seller){
		return DefaultProductGroupResponseDto.from(productGroup, seller);
	}

	private ProductNoticeResponseDto toProductNoticeResponseDto(ProductNotice productNotice){
		return ProductNoticeResponseDto.from(productNotice);
	}

	private ProductDeliveryResponseDto toProductDeliveryResponseDto(ProductDelivery productDelivery){
		return ProductDeliveryResponseDto.from(productDelivery);
	}

	private ProductDetailDescriptionResponseDto toProductDetailDescriptionResponseDto(
		ProductDetailDescription productDetailDescription){
		return ProductDetailDescriptionResponseDto.from(productDetailDescription);
	}

	private ProductBrandResponseDto toBrandResponseDto(Brand brand){
		return ProductBrandResponseDto.from(brand);
	}

	private List<ProductCategoryResponseDto> toCategoryResponseDto(List<? extends Category> categories){
		return categories.stream().map(ProductCategoryResponseDto::from).toList();
	}

	private List<ProductGroupImageResponseDto> toProductGroupImageResponseDto(ProductGroupImageContext productGroupImageContext){
		return productGroupImageContext.getImages().stream()
			.map(ProductGroupImageResponseDto::from)
			.toList();
	}

	private List<ProductResponseDto> toProducts(ProductOptionContext productOptionContext){
		return productOptionContext.getProducts().stream()
			.map(ProductResponseDto::from)
			.toList();
	}

	public DefaultProductGroupResponseDto getProductGroup() {
		return productGroup;
	}

	public ProductNoticeResponseDto getProductNotice() {
		return productNotice;
	}

	public ProductDeliveryResponseDto getProductDelivery() {
		return productDelivery;
	}

	public ProductDetailDescriptionResponseDto getProductDetailDescription() {
		return productDetailDescription;
	}

	public List<ProductGroupImageResponseDto> getProductGroupImages() {
		return productGroupImages;
	}

	public List<ProductResponseDto> getProducts() {
		return products;
	}

	public ProductBrandResponseDto getBrand() {
		return brand;
	}

	public List<ProductCategoryResponseDto> getCategories() {
		return categories;
	}
}
