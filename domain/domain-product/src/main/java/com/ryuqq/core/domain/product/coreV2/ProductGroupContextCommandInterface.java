package com.ryuqq.core.domain.product.coreV2;

import com.ryuqq.core.domain.product.core.ItemContext;

public interface ItemContextCommandInterface {

	long registerItemContext(ItemContext itemContext);
	long updateItemContext(long id, ItemContext itemContext);

}
