package com.ryuqq.core.domain.product.core;

public interface ProductGroupDomainValidator<T> {

	boolean supports(Object clazz);
	void validate(T target, ValidationResult result, boolean updated);


}
