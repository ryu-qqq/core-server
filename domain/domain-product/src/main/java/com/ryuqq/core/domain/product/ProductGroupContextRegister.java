package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.sync.DefaultProductSyncCommand;
import com.ryuqq.core.domain.product.dao.sync.ProductSyncCommand;
import com.ryuqq.core.domain.product.dao.sync.ProductSyncPersistenceRepository;
import com.ryuqq.core.enums.SyncStatus;

@Component
public class ProductGroupContextRegister {

	private final ProductGroupDomainHandler productGroupDomainHandler;
	private final ProductGroupImageDomainHandler productGroupImageDomainHandler;
	private final ProductDomainHandler productDomainHandler;
	private final ProductSyncPersistenceRepository productSyncPersistenceRepository;

	public ProductGroupContextRegister(ProductGroupDomainHandler productGroupDomainHandler,
									   ProductGroupImageDomainHandler productGroupImageDomainHandler,
									   ProductDomainHandler productDomainHandler,
									   ProductSyncPersistenceRepository productSyncPersistenceRepository) {
		this.productGroupDomainHandler = productGroupDomainHandler;
		this.productGroupImageDomainHandler = productGroupImageDomainHandler;
		this.productDomainHandler = productDomainHandler;
		this.productSyncPersistenceRepository = productSyncPersistenceRepository;
	}

	public void registerProductGroupContext(ProductGroupContext productGroupContext){

		ProductGroupContext assignProductGroupIdContext = saveProductGroupAndAssignProductGroupId(productGroupContext);

		productGroupDomainHandler.handleProductDeliveryDomain(assignProductGroupIdContext.getProductDelivery());
		productGroupDomainHandler.handleProductNoticeDomain(assignProductGroupIdContext.getProductNotice());

		productGroupImageDomainHandler.handleProductImageDomain(assignProductGroupIdContext.getProductGroupImages());
		productGroupImageDomainHandler.handleProductDetailDescriptionDomain(assignProductGroupIdContext.getProductDetailDescription());

		productDomainHandler.handleProductDomain(assignProductGroupIdContext.getProducts());

		ProductSyncCommand productSyncCommand = new DefaultProductSyncCommand(productGroupContext.getProductGroupId(), SyncStatus.APPROVED);
		productSyncPersistenceRepository.save(productSyncCommand);

	}

	private ProductGroupContext saveProductGroupAndAssignProductGroupId(ProductGroupContext productGroupContext){
		long productGroupId = productGroupDomainHandler.handleProductGroupDomain(productGroupContext.getProductGroup());
		return productGroupContext.assignProductGroupId(productGroupId);
	}

}
