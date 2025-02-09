package com.ryuqq.core.domain.product.dao.options.mapping;

import java.util.List;

import com.ryuqq.core.domain.product.core.OptionContextCommand;

public interface ProductOptionPersistenceRepository {

	void save(OptionContextCommand optionContextCommand);
	void saveAll(List<OptionContextCommand> optionContextCommands);
	void update(OptionContextCommand optionContextCommand);
	void updateAll(List<OptionContextCommand> optionContextCommands);
	void deleteAll(List<Long> productIds);
}
