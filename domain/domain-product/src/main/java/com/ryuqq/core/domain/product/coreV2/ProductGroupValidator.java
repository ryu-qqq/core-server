package com.ryuqq.core.domain.product.coreV2;

public interface ProductGroupDomainValidator<T> {

	boolean supports(Class<?> clazz);
	void validate(T target, ValidationResult result, boolean updated);


}
