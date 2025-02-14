package com.ryuqq.core.storage.db.external;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExternalProductJdbcRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ExternalProductJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public int[] batchInsertExternalProducts(List<ExternalProductEntity> externalProductEntities) {
		String sql = "INSERT INTO EXTERNAL_PRODUCT " +
			"(EXTERNAL_PRODUCT_GROUP_ID, EXTERNAL_PRODUCT_ID, PRODUCT_ID, OPTION_VALUE, QUANTITY, ADDITIONAL_PRICE, " +
			"SOLD_OUT, DISPLAYED) " +
			"VALUES (:externalProductGroupId, :externalProuctId, :productId, :optionValue, :quantity, :additionalPrice, :soldOut, :displayed)";

		List<Map<String, Object>> batchValues = externalProductEntities.stream()
			.map(product -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("externalProductGroupId", product.getExternalProductGroupId())
					.addValue("externalProductId", product.getExternalProductId())
					.addValue("productId", product.getProductId())
					.addValue("optionValue", product.getOptionValue())
					.addValue("quantity", product.getQuantity())
					.addValue("additionalPrice", product.getAdditionalPrice())
					.addValue("soldOut", product.isSoldOut())
					.addValue("displayed", product.isDisplayed());
				return params.getValues();
			})
			.toList();

		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

	public int[] batchUpdateExternalProduct(List<ExternalProductEntity> externalProductEntities) {
		String sql = "UPDATE EXTERNAL_PRODUCT SET " +
			"EXTERNAL_PRODUCT_GROUP_ID = :externalProductGroupId, " +
			"EXTERNAL_PRODUCT_ID = :externalProductId, " +
			"PRODUCT_ID = :productId, " +
			"OPTION_VALUE = :optionValue, " +
			"QUANTITY = :quantity, " +
			"ADDITIONAL_PRICE = :additionalPrice, " +
			"SOLD_OUT = :soldOut, " +
			"DISPLAYED = :displayed, " +
			"DELETED = :deleted, " +
			"UPDATED_AT = :updatedAt " +
			"WHERE EXTERNAL_PRODUCT_GROUP_ID = :externalProductGroupId AND PRODUCT_ID = :productId ";

		List<Map<String, Object>> batchValues = externalProductEntities.stream()
			.map(product -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("externalProductGroupId", product.getExternalProductGroupId())
					.addValue("externalProductId", product.getExternalProductId())
					.addValue("productId", product.getProductId())
					.addValue("optionValue", product.getOptionValue())
					.addValue("quantity", product.getQuantity())
					.addValue("additionalPrice", product.getAdditionalPrice())
					.addValue("soldOut", product.isSoldOut())
					.addValue("displayed", product.isDisplayed())
					.addValue("deleted", product.isDeleted())
					.addValue("updatedAt", LocalDateTime.now());

				return params.getValues();
			})
			.toList();

		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

}
