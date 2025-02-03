package com.ryuqq.core.domain.external;

import com.ryuqq.core.enums.ProductImageType;

public record ExternalProductGroupImageRequest(
	ProductImageType productImageType,
	String imageUrl,
	int order
) {
}
