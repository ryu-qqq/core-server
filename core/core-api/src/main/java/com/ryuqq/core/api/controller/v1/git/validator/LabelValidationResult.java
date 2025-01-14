package com.ryuqq.core.api.controller.v1.git.validator;

import com.ryuqq.core.domain.git.Label;

import java.util.List;
import java.util.Objects;

public final class LabelValidationResult {
	private final boolean valid;
	private final List<String> invalidLabels;


	public LabelValidationResult(List<String> invalidLabels) {
		this.invalidLabels = invalidLabels;
		this.valid = invalidLabels.isEmpty();
	}

	public boolean isValid() {
		return valid;
	}

	public List<String> getInvalidLabels() {
		return invalidLabels;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj
			== this) return true;
		if (obj
			== null
			|| obj.getClass()
			!= this.getClass()) return false;
		var that = (LabelValidationResult) obj;
		return this.valid
			== that.valid
			&&
			Objects.equals(this.invalidLabels, that.invalidLabels);
	}

	@Override
	public int hashCode() {
		return Objects.hash(valid, invalidLabels);
	}

	@Override
	public String toString() {
		return "LabelValidationResult["
			+
			"valid="
			+ valid
			+ ", "
			+
			"invalidLabels="
			+ invalidLabels
			+ ']';
	}

}
