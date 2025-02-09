package com.ryuqq.core.domain.product.core;

public interface UpdateChecker<T, U>  {

	void checkUpdates(UpdateDecision decision, T existing, U updated);
	boolean supports(Object fieldValue);


}
