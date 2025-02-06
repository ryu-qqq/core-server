package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateDecision;

@Component
public class ProductGroupContextUpdater {

	private final ProductGroupContextFinder productGroupContextFinder;
	private final UpdateCheckerExecutor updateCheckerExecutor; // Executor 추가
	private final UpdateDecisionExecutor updateDecisionExecutor;

	public ProductGroupContextUpdater(ProductGroupContextFinder productGroupContextFinder,
									  UpdateCheckerExecutor updateCheckerExecutor,
									  UpdateDecisionExecutor updateDecisionExecutor) {
		this.productGroupContextFinder = productGroupContextFinder;
		this.updateCheckerExecutor = updateCheckerExecutor;
		this.updateDecisionExecutor = updateDecisionExecutor;
	}

	public UpdateDecision updateProductGroupContext(long productGroupId, ProductGroupContext updatedContext) {
		ProductGroupContext existingContext = productGroupContextFinder.fetchById(productGroupId);
		UpdateDecision decision = new UpdateDecision();

		compareAndUpdateFields(decision, productGroupId, existingContext, updatedContext);

		updateDecisionExecutor.execute(decision);

		return decision;
	}

	private void compareAndUpdateFields(UpdateDecision decision, long productGroupId, ProductGroupContext existingContext, ProductGroupContext updatedContext) {
		compareField(decision, productGroupId, existingContext.getProductGroup(), updatedContext.getProductGroup());
		compareField(decision, productGroupId, existingContext.getProductNotice(), updatedContext.getProductNotice());
		compareField(decision, productGroupId, existingContext.getProductDelivery(), updatedContext.getProductDelivery());
		compareField(decision, productGroupId, existingContext.getProductDetailDescription(), updatedContext.getProductDetailDescription());
		compareField(decision, productGroupId, existingContext.getProductGroupImages(), updatedContext.getProductGroupImages());
		compareField(decision, productGroupId, existingContext.getProducts(), updatedContext.getProducts());
	}

	private <T> void compareField(UpdateDecision decision, long productGroupId, T existingField, T updatedField) {
		updateCheckerExecutor.executeChecker(decision, productGroupId, existingField, updatedField);
	}
}
