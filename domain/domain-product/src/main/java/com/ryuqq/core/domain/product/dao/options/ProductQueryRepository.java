package com.ryuqq.core.domain.product.dao.options;

import java.util.List;

import com.ryuqq.core.domain.product.ProductContextBundle;

public interface ProductQueryRepository {

	ProductContextBundle fetchByProductGroupIds(List<Long> productGroupIds);

}
