package com.ryuqq.core.domain.external.dao.group;

import java.math.BigDecimal;

import com.ryuqq.core.enums.SyncStatus;

public interface ExternalProductGroupCommand{
	Long id();
	long siteId();
	long productGroupId();
	long brandId();
	long categoryId();
	String externalProductGroupId();
	String productName();
	BigDecimal regularPrice();
	BigDecimal currentPrice();
	SyncStatus status();
	boolean fixedPrice();
	boolean soldOut();
	boolean displayed();
}
