package com.ryuqq.core.storage.db.product.option;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ProductJdbcRepository {

	private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductJdbcRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

	public List<Long> batchInsertProductsAndGetIds(List<ProductEntity> productEntities) {
		int[] insertCounts = batchInsertProducts(productEntities);
		int numberOfInsertedRows = insertCounts.length;
		return getInsertedIds(numberOfInsertedRows);
	}


    public int[] batchInsertProducts(List<ProductEntity> productEntities) {
        String sql = "INSERT INTO PRODUCT " +
                "(PRODUCT_GROUP_ID, SOLD_OUT, DISPLAYED, QUANTITY, ADDITIONAL_PRICE) " +
                "VALUES (:productGroupId, :soldOut, :displayed, :quantity, :additionalPrice)";

        List<Map<String, Object>> batchValues = productEntities.stream()
                .map(product -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", product.getProductGroupId())
                            .addValue("soldOut", product.isSoldOut())
                            .addValue("displayed", product.isDisplayed())
                            .addValue("quantity", product.getQuantity())
                            .addValue("additionalPrice", product.getAdditionalPrice());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public int[] batchUpdateProducts(List<ProductEntity> productEntities) {
        String sql = "UPDATE PRODUCT " +
                "SET SOLD_OUT = :soldOut, " +
                " DISPLAYED = :displayed, " +
                " QUANTITY = :quantity, " +
                " ADDITIONAL_PRICE = :additionalPrice, " +
                " DELETED = :deleted, " +
				" UPDATED_AT = :updateAt " +
                " WHERE ID = :productId ";

        List<Map<String, Object>> batchValues = productEntities.stream()
                .map(product -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productId", product.getId())
                            .addValue("deleted", product.isDeleted())
                            .addValue("soldOut", product.isSoldOut())
                            .addValue("displayed", product.isDisplayed())
                            .addValue("quantity", product.getQuantity())
							.addValue("updateAt", LocalDateTime.now())

						.addValue("additionalPrice", product.getAdditionalPrice());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public void softDeleteAll(List<Long> productIds) {
        String sql = "UPDATE PRODUCT " +
                "SET DELETED = :deleted, " +
				"UPDATED_AT = :updateAt " +
				"WHERE PRODUCT_ID = :productId ";

        List<Map<String, Object>> batchValues = productIds.stream()
                .map(Long -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productId", Long)
							.addValue("updateAt", LocalDateTime.now())
							.addValue("deleted", true);
                    return params.getValues();
                })
                .toList();

        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }


	private List<Long> getInsertedIds(int numberOfRows) {
		long firstInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
		return LongStream.range(firstInsertedId, firstInsertedId + numberOfRows)
			.boxed()
			.toList();
	}



}
