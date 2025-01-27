package com.ryuqq.core.domain.external.dao.group;

import java.math.BigDecimal;

import com.ryuqq.core.enums.SyncStatus;

public record UpdateExternalProductGroupCommand(
	Long id,
	long siteId,
	long productGroupId,
	String externalProductGroupId,
	long brandId,
	long categoryId,
	String productName,
	BigDecimal regularPrice,
	BigDecimal currentPrice,
	SyncStatus status,
	boolean fixedPrice,
	boolean soldOut,
	boolean displayed
) implements ExternalProductGroupCommand {
}
