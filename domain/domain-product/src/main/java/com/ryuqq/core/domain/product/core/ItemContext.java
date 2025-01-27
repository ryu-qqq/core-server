package com.ryuqq.core.domain.product.core;

import java.util.List;

public interface ItemContext {

	Item getItem();
	ItemNoticeInfo getNoticeInfo();
	ItemDeliveryInfo getDeliveryInfo();
	ItemRefundInfo getRefundInfo();
	List<? extends ItemImage> getItemImages();
	String getItemDescription();

}
