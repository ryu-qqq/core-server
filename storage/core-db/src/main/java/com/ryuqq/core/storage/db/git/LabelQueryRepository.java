package com.ryuqq.core.storage.db.git;

import java.util.List;

import com.ryuqq.core.storage.db.git.dto.LabelDto;

public interface LabelQueryRepository {

	List<LabelDto> fetchByLabelIn(List<String> names);

}
