package com.ryuqq.core.domain.product;

public interface ProductGroupDomainValidator<T> {

	boolean supports(Class<?> clazz);
	void validate(T target, ValidationResult result, boolean updated);


}
