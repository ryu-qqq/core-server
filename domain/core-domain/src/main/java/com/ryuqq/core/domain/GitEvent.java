package com.ryuqq.core.domain;

import java.util.List;

public record GitEvent(
	Branch branch,
	List<ChangedFile> changedFiles
) {
}
