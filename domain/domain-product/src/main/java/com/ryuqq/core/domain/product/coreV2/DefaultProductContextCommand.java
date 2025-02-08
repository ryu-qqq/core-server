package com.ryuqq.core.domain.product.coreV2;

import java.util.List;

public record DefaultProductGroupImageContextCommand(

	List<? extends ProductGroupImageCommand> productGroupImageCommands

) implements ProductGroupImageContextCommand {

}
