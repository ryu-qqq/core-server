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

		compareAndUpdateFields(decision, existingContext, updatedContext);

		updateDecisionExecutor.execute(decision);

		return decision;
	}

	private void compareAndUpdateFields(UpdateDecision decision, ProductGroupContext existingContext, ProductGroupContext updatedContext) {
		compareField(decision, existingContext.getProductGroup(), updatedContext.getProductGroup());
		compareField(decision, existingContext.getProductNotice(), updatedContext.getProductNotice());
		compareField(decision, existingContext.getProductDelivery(), updatedContext.getProductDelivery());
		compareField(decision, existingContext.getProductDetailDescription(), updatedContext.getProductDetailDescription());
		compareField(decision, existingContext.getProductGroupImages(), updatedContext.getProductGroupImages());
		compareField(decision, existingContext.getProducts(), updatedContext.getProducts());
	}

	private <T> void compareField(UpdateDecision decision, T existingField, T updatedField) {
		updateCheckerExecutor.executeChecker(decision, existingField, updatedField);
	}
}
