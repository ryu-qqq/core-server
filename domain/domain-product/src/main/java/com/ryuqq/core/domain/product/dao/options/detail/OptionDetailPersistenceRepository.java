package com.ryuqq.core.domain.product.dao.options.detail;

import java.util.List;

import com.ryuqq.core.domain.product.core.OptionDetailCommand;

public interface OptionDetailPersistenceRepository {

	long save(OptionDetailCommand optionDetailCommand);
	List<Long> saveAll(List<OptionDetailCommand> optionDetailCommands);
	void update(OptionDetailCommand optionDetailCommand);
	void updateAll(List<OptionDetailCommand> optionDetailCommands);

}
