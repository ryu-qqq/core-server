package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductContextUpdateProcessor implements UpdateProcessor<ProductContextBundle> {

	private final ProductDomainHandler productDomainHandler;

	public ProductContextUpdateProcessor(ProductDomainHandler productDomainHandler) {
		this.productDomainHandler = productDomainHandler;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductContextBundle.class.equals(domainType);
	}

	@Override
	public void processUpdate(ProductContextBundle entity) {
		List<ProductContext> toInserts = new ArrayList<>();
		List<ProductContext> toUpdates = new ArrayList<>();


		for(ProductContext productCommand : entity.getProducts()) {
			if(productCommand.getId() != null){
				toUpdates.add(productCommand);
			}else{
				toInserts.add(productCommand);
			}
		}

		if(!toInserts.isEmpty()){
			productDomainHandler.handleProductDomain(new ProductContextBundle(toInserts));
		}

		if(!toUpdates.isEmpty()){
			productDomainHandler.updateProductDomain(new ProductContextBundle(toUpdates));
		}
	}


}
