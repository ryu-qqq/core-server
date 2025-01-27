package com.ryuqq.core.domain.product.dao.options.detail;

import java.util.List;

public interface OptionDetailPersistenceRepository {

	long save(OptionDetailCommand optionDetailCommand);
	List<Long> saveAll(List<OptionDetailCommand> optionDetailCommands);
	void update(OptionDetailCommand optionDetailCommand);
	void updateAll(List<OptionDetailCommand> optionDetailCommands);
}
