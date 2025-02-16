package com.ryuqq.core.domain.product;

public interface UpdateProcessor<T> {
	boolean supports(Class<?> domainType);
	void processUpdate(T entity);

}
