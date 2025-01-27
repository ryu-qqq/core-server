package com.ryuqq.core.domain.product.dao.options.group;

import java.util.List;

public interface OptionGroupPersistenceRepository {

	long save(OptionGroupCommand optionGroupCommand);
	List<Long> saveAll(List<OptionGroupCommand> optionGroupCommands);
	void update(OptionGroupCommand optionGroupCommand);
	void updateAll(List<OptionGroupCommand> optionGroupCommands);

}
