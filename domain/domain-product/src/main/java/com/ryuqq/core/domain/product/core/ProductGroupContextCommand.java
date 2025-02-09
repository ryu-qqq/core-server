package com.ryuqq.core.domain.product.core;

public interface ProductGroupContextCommand {

	ProductGroupCommand getProductGroupCommand();
	ProductNoticeCommand getProductNoticeCommand();
	ProductDeliveryCommand getProductDeliveryCommand();
	ProductDetailDescriptionCommand getProductDetailDescriptionCommand();
	ProductGroupImageContextCommand getProductGroupImageCommandContextCommand();
	ProductOptionContextCommand getProductCommandContextCommand();

	default EssentialProductGroupInfo getEssentialProductGroupInfo() {
		return new EssentialProductGroupInfo(
			getProductGroupCommand(),
			getProductNoticeCommand(),
			getProductDeliveryCommand()
		);
	}

	default EssentialProductImageInfo getEssentialProductImageInfo() {
		return new EssentialProductImageInfo(
			getProductDetailDescriptionCommand(),
			getProductGroupImageCommandContextCommand()
		);
	}

	record EssentialProductGroupInfo(ProductGroupCommand productGroupCommand,
									 ProductNoticeCommand productNoticeCommand,
									 ProductDeliveryCommand productDeliveryCommand) {
	}

	record EssentialProductImageInfo(ProductDetailDescriptionCommand productDetailDescription,
									 ProductGroupImageContextCommand productGroupImageContextCommand) {
	}

}
