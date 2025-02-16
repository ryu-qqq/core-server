package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;

@Component
public class ProductContextUpdateProcessor implements UpdateProcessor<ProductOptionContextCommand> {

	private final ProductDomainHandler productDomainHandler;

	public ProductContextUpdateProcessor(ProductDomainHandler productDomainHandler) {
		this.productDomainHandler = productDomainHandler;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductOptionContextCommand.class.isAssignableFrom(domainType);
	}

	@Override
	public void processUpdate(ProductOptionContextCommand productOptionContextCommand) {
		List<ProductOptionCommand> toInserts = new ArrayList<>();
		List<ProductOptionCommand> toUpdates = new ArrayList<>();


		for(ProductOptionCommand productOptionCommand : productOptionContextCommand.productCommands()) {
			ProductCommand productCommand = productOptionCommand.productCommand();

			if(productCommand.id() != null){
				toUpdates.add(productOptionCommand);
			}else{
				toInserts.add(productOptionCommand);
			}
		}

		if(!toInserts.isEmpty()){
			ProductOptionContextCommand toInsertCommand = ProductOptionContextCommand.of(productOptionContextCommand.productGroupId(), productOptionContextCommand.optionType(), toInserts);
			productDomainHandler.handle(productOptionContextCommand.productGroupId(), toInsertCommand);
		}

		if(!toUpdates.isEmpty()){
			ProductOptionContextCommand toUpdateCommand = ProductOptionContextCommand.of(productOptionContextCommand.productGroupId(), productOptionContextCommand.optionType(), toUpdates);
			productDomainHandler.handle(toUpdateCommand);
		}
	}


}
