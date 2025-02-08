package com.ryuqq.core.domain.product.coreV2;

import java.util.List;

public interface ProductGroupCommandContext {

	ProductGroup getProductGroup();
	ProductNotice getProductNotice();
	ProductDelivery getProductDelivery();
	ProductRefundNotice getProductRefundNotice();
	String getProductDetailDescription();
	List<? extends ProductGroupImage> getProductGroupImages();
	List<? extends Product> getProducts();


}
