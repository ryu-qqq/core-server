package com.ryuqq.core.api.controller.v1.product.mapper;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;

public interface ProductGroupContextCommandFactory {

	ProductGroupContextCommand createCommand(Long productGroupId, ProductGroupContextCommandRequestDto dto);

}
