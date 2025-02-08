package com.ryuqq.core.domain.product.coreV2;

import java.util.List;

public interface ProductCommandContext {

	List<? extends ProductCommand> getProductCommands();

}
