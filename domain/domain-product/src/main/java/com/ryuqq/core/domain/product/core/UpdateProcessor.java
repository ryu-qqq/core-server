package com.ryuqq.core.domain.product.core;

public interface UpdateProcessor<T> {
	boolean supports(Class<?> domainType);

	void processUpdate(T entity);

}
