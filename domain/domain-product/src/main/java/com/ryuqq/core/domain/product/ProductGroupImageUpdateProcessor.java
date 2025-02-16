package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;

@Component
public class ProductGroupImageUpdateProcessor implements UpdateProcessor<ProductGroupImageContextCommand> {

	private final ProductGroupImageRegister productGroupImageRegister;

	public ProductGroupImageUpdateProcessor(ProductGroupImageRegister productGroupImageRegister) {
		this.productGroupImageRegister = productGroupImageRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductGroupImageContextCommand.class.isAssignableFrom(domainType);
	}

	@Override
	public void processUpdate(ProductGroupImageContextCommand productGroupImageContextCommand) {

		List<? extends ProductGroupImageCommand> insertProductGroupImageCommands =
			productGroupImageContextCommand.getInsertProductGroupImageCommands();

		if(!insertProductGroupImageCommands.isEmpty()){
			productGroupImageRegister.register(insertProductGroupImageCommands);
		}

		List<? extends ProductGroupImageCommand> updateProductGroupImageCommands =
			productGroupImageContextCommand.getUpdateProductGroupImageCommands();

		if(!updateProductGroupImageCommands.isEmpty()){
			productGroupImageRegister.update(updateProductGroupImageCommands);
		}

	}

}
