package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.dao.notice.ProductNoticeCommand;

@Component
public class ProductGroupDomainHandler {

	private final ProductGroupRegister productGroupRegister;
	private final ProductDeliveryRegister productDeliveryRegister;
	private final ProductNoticeRegister productNoticeRegister;

	public ProductGroupDomainHandler(ProductGroupRegister productGroupRegister,
									 ProductDeliveryRegister productDeliveryRegister,
									 ProductNoticeRegister productNoticeRegister) {
		this.productGroupRegister = productGroupRegister;
		this.productDeliveryRegister = productDeliveryRegister;
		this.productNoticeRegister = productNoticeRegister;
	}

	public long handle(ProductGroupContextCommand.EssentialProductGroupInfo essentialProductGroupInfo) {
		long productGroupId = handleProductGroupDomain(essentialProductGroupInfo.productGroupCommand());
		handleProductDeliveryDomain(productGroupId, essentialProductGroupInfo.productDeliveryCommand());
		handleProductNoticeDomain(productGroupId, essentialProductGroupInfo.productNoticeCommand());
		return productGroupId;
	}

	private long handleProductGroupDomain(ProductGroupCommand productGroup) {
		return productGroupRegister.register(productGroup);
	}

	private void handleProductDeliveryDomain(long productGroupId, ProductDeliveryCommand productDeliveryCommand) {
		ProductDeliveryCommand assignedProductDeliveryCommand = productDeliveryCommand.assignProductGroupId(productGroupId);
		productDeliveryRegister.register(assignedProductDeliveryCommand);
	}

	private void handleProductNoticeDomain(long productGroupId, ProductNoticeCommand productNoticeCommand) {
		ProductNoticeCommand assignedProductNoticeCommand = productNoticeCommand.assignProductGroupId(productGroupId);
		productNoticeRegister.register(assignedProductNoticeCommand);
	}

}
