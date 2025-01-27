package com.ryuqq.core.domain.category;

import java.util.List;

public record CategoryRelation(
	long categoryId,
	List<DefaultCategory> categories,
	boolean parentRelation
) {
}
