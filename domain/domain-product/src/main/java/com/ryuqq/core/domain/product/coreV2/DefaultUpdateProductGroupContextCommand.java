package com.ryuqq.core.domain.product.coreV2;

import java.util.List;

public class DefaultProductGroupContextCommand implements CreateProductGroupContextCommand {


	@Override
	public ProductGroup getProductGroup() {
		return null;
	}

	@Override
	public ProductNotice getProductNotice() {
		return null;
	}

	@Override
	public ProductDelivery getProductDelivery() {
		return null;
	}

	@Override
	public ProductRefundNotice getProductRefundNotice() {
		return null;
	}

	@Override
	public String getProductDetailDescription() {
		return "";
	}

	@Override
	public List<? extends ProductGroupImage> getProductGroupImages() {
		return List.of();
	}

	@Override
	public List<? extends Product> getProducts() {
		return List.of();
	}

}
