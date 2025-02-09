package com.ryuqq.core.domain.product.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.category.core.CategoryQueryInterface;
import com.ryuqq.core.domain.seller.core.SellerQueryInterface;

@Component
public class ProductGroupValidator implements ProductGroupDomainValidator<ProductGroupCommand> {

	private final BrandQueryInterface brandQueryInterface;
	private final CategoryQueryInterface categoryQueryInterface;
	private final SellerQueryInterface sellerQueryInterface;

	public ProductGroupValidator(BrandQueryInterface brandQueryInterface, CategoryQueryInterface categoryQueryInterface,
								 SellerQueryInterface sellerQueryInterface) {
		this.brandQueryInterface = brandQueryInterface;
		this.categoryQueryInterface = categoryQueryInterface;
		this.sellerQueryInterface = sellerQueryInterface;
	}

	@Override
	public boolean supports(Object clazz) {
		return clazz instanceof ProductGroupCommand;
	}

	@Override
	public void validate(ProductGroupCommand target, ValidationResult result, boolean updated) {
		long brandId = target.brandId();
		long categoryId = target.categoryId();
		long sellerId = target.sellerId();


		boolean brandExist = brandQueryInterface.existById(brandId);
		if(!brandExist){
			result.addError(String.format("Brand Id Not Exist : %s", brandId));
		}


		boolean categoryExist = categoryQueryInterface.existById(categoryId);
		if(!categoryExist){
			result.addError(String.format("Category Id Not Exist : %s", categoryId));
		}

		boolean sellerExist = sellerQueryInterface.existById(sellerId);
		if(!sellerExist){
			result.addError(String.format("Seller Id Not Exist : %s", sellerId));
		}

	}
}
