package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;

@Component
public class ProductGroupContextUpdater {

	private final ProductGroupContextFinder productGroupContextFinder;
	private final UpdateCheckerExecutor updateCheckerExecutor;
	private final UpdateDecisionExecutor updateDecisionExecutor;

	public ProductGroupContextUpdater(ProductGroupContextFinder productGroupContextFinder,
									  UpdateCheckerExecutor updateCheckerExecutor,
									  UpdateDecisionExecutor updateDecisionExecutor) {
		this.productGroupContextFinder = productGroupContextFinder;
		this.updateCheckerExecutor = updateCheckerExecutor;
		this.updateDecisionExecutor = updateDecisionExecutor;
	}

	public UpdateDecision updateProductGroupContext(long id, ProductGroupContextCommand productGroupContextCommand) {
		ProductGroupContext productGroupContext = productGroupContextFinder.fetchById(id);
		UpdateDecision decision = new UpdateDecision();

		compareAndUpdateFields(decision, productGroupContext, productGroupContextCommand);

		updateDecisionExecutor.execute(decision);

		return decision;
	}

	private void compareAndUpdateFields(UpdateDecision decision, ProductGroupContext productGroupContext, ProductGroupContextCommand productGroupContextCommand) {
		compareField(decision, productGroupContext.getProductGroup(), productGroupContextCommand.getProductGroupCommand());
		compareField(decision, productGroupContext.getProductNotice(), productGroupContextCommand.getProductNoticeCommand());
		compareField(decision, productGroupContext.getProductDelivery(), productGroupContextCommand.getProductDeliveryCommand());
		compareField(decision, productGroupContext.getProductDetailDescription(), productGroupContextCommand.getProductDetailDescriptionCommand());
		compareField(decision, productGroupContext.getProductGroupImageContext(), productGroupContextCommand.getProductGroupImageContextCommand());
		compareField(decision, productGroupContext.getProductOptionContext(), productGroupContextCommand.getProductOptionContextCommand());
	}

	private <T, U> void compareField(UpdateDecision decision, T existingField, U updatedField) {
		updateCheckerExecutor.executeChecker(decision, existingField, updatedField);
	}
}
