package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.git.dto.LabelDto;

import java.util.List;
import java.util.Optional;

public interface LabelQueryRepository {

	Optional<LabelDto> fetchByLabels(List<String> labels);

}
