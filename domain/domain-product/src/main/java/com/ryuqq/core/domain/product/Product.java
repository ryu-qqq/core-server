package com.ryuqq.core.domain.product;

import java.math.BigDecimal;
import java.util.Objects;

import com.ryuqq.core.domain.product.dao.options.CreateProductCommand;
import com.ryuqq.core.domain.product.dao.options.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.UpdateProductCommand;

public class Product {

	private final Long id;
	private final Long productGroupId;
	private final boolean soldOut;
	private final boolean displayed;
	private final int quantity;
	private final BigDecimal additionalPrice;
	private final boolean deleted;


	private Product(Long id, Long productGroupId, boolean soldOut, boolean displayed, int quantity,
				   BigDecimal additionalPrice, boolean deleted) {
		this.id = id;
		this.productGroupId = productGroupId;
		this.soldOut = soldOut;
		this.displayed = displayed;
		this.quantity = quantity;
		this.additionalPrice = additionalPrice;
		this.deleted = deleted;
	}

	public static Product create(boolean soldOut, boolean displayed, int quantity,
								 BigDecimal additionalPrice, boolean deleted){
		return new Product(null, null, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	public static Product create(Long id, Long productGroupId, boolean soldOut, boolean displayed, int quantity,
								 BigDecimal additionalPrice, boolean deleted){
		return new Product(id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	public Product assignProductGroupId(Long productGroupId) {
		return new Product(this.id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	public ProductCommand toCommand(){
		if(this.id != null){
			return new UpdateProductCommand(id, productGroupId, soldOut, displayed, quantity, additionalPrice ,deleted);
		}

		return new CreateProductCommand(productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	public Long getId() {
		return id;
	}

	public Long getProductGroupId() {
		return productGroupId;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getAdditionalPrice() {
		return additionalPrice;
	}

	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		Product product = (Product) object;
		return soldOut
			== product.soldOut
			&& displayed
			== product.displayed
			&& quantity
			== product.quantity
			&& deleted
			== product.deleted
			&& Objects.equals(id, product.id)
			&& Objects.equals(productGroupId, product.productGroupId)
			&& Objects.equals(additionalPrice, product.additionalPrice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productGroupId, soldOut, displayed, quantity, additionalPrice, deleted);
	}

	@Override
	public String toString() {
		return "Product{"
			+
			"id="
			+ id
			+
			", productGroupId="
			+ productGroupId
			+
			", soldOut="
			+ soldOut
			+
			", displayed="
			+ displayed
			+
			", quantity="
			+ quantity
			+
			", additionalPrice="
			+ additionalPrice
			+
			", deleted="
			+ deleted
			+
			'}';
	}
}
