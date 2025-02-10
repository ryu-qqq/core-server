package com.ryuqq.core.domain.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.DefaultProductNotice;
import com.ryuqq.core.domain.product.core.ProductNotice;
import com.ryuqq.core.domain.product.core.ProductNoticeCommand;
import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductNoticeChecker implements UpdateChecker<ProductNotice, ProductNoticeCommand> {

	@Override
	public void checkUpdates(UpdateDecision decision, ProductNotice existing, ProductNoticeCommand updated) {
		boolean anyChangeDetected = hasUpdates(existing, updated);

		if (anyChangeDetected) {
			decision.addUpdate(updated, ProductDomainEventType.NOTICE,false);
		}

	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof DefaultProductNotice;
	}

	private boolean hasUpdates(ProductNotice existing, ProductNoticeCommand updated) {
		return !Objects.equals(existing.getMaterial(), updated.material()) ||
			!Objects.equals(existing.getColor(), updated.color()) ||
			!Objects.equals(existing.getSize(), updated.size()) ||
			!Objects.equals(existing.getMaker(), updated.maker()) ||
			existing.getOrigin() != updated.origin() ||
			!Objects.equals(existing.getWashingMethod(), updated.washingMethod()) ||
			!Objects.equals(existing.getYearMonth(), updated.yearMonth()) ||
			!Objects.equals(existing.getAssuranceStandard(), updated.assuranceStandard()) ||
			!Objects.equals(existing.getAsPhone(), updated.asPhone());
	}


}
