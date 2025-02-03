package com.ryuqq.core.domain.git;

import java.util.List;

public interface LabelQueryRepository {

	List<Label> fetchByLabelIn(List<String> names);

}
