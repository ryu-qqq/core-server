package com.ryuqq.core.storage.db.product.group;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductGroupJdbcRepository {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductGroupJdbcRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

	public List<Long> batchInsertProductGroupsAndGetIds(List<ProductGroupEntity> productGroups) {
		int[] insertCounts = batchInsertProductGroups(productGroups);
		int numberOfInsertedRows = insertCounts.length;
		List<Long> insertedIds = getInsertedIds(numberOfInsertedRows);
		return insertedIds;
	}

	public int[] batchInsertProductGroups(List<ProductGroupEntity> productGroups) {
        String sql = "INSERT INTO PRODUCT_GROUP " +
                "(ID, SELLER_ID, CATEGORY_ID, BRAND_ID, PRODUCT_GROUP_NAME, STYLE_CODE, PRODUCT_CONDITION, " +
                "MANAGEMENT_TYPE, OPTION_TYPE, REGULAR_PRICE, CURRENT_PRICE, DISCOUNT_RATE, SOLD_OUT, " +
                "DISPLAYED, PRODUCT_STATUS, KEYWORDS) " +
                "VALUES (:id, :sellerId, :categoryId, :brandId, :productGroupName, :styleCode, :productCondition, " +
                ":managementType, :optionType, :regularPrice, :currentPrice, :discountRate, :soldOut, " +
                ":displayed, :productStatus, :keywords)";

        List<Map<String, Object>> batchValues = productGroups.stream()
                .map(group -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
							.addValue("id", group.getId())
							.addValue("sellerId", group.getSellerId())
                            .addValue("categoryId", group.getCategoryId())
                            .addValue("brandId", group.getBrandId())
                            .addValue("productGroupName", group.getProductGroupName())
                            .addValue("styleCode", group.getStyleCode())
                            .addValue("productCondition", group.getProductCondition().toString())
                            .addValue("managementType", group.getManagementType().toString())
                            .addValue("optionType", group.getOptionType().toString())
                            .addValue("regularPrice", group.getRegularPrice())
                            .addValue("currentPrice", group.getCurrentPrice())
                            .addValue("discountRate", group.getDiscountRate())
                            .addValue("soldOut", group.isSoldOut())
                            .addValue("displayed", group.isDisplayed())
                            .addValue("productStatus", group.getProductStatus().toString())
                            .addValue("keywords", group.getKeywords());

                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public int[] batchUpdateProductGroups(List<ProductGroupEntity> productGroups) {
        String sql = "UPDATE PRODUCT_GROUP " +
                "SET CATEGORY_ID = :categoryId, " +
                "BRAND_ID = :brandId, " +
				"SELLER_ID = :sellerId, " +
				"PRODUCT_GROUP_NAME = :productGroupName, " +
                "STYLE_CODE = :styleCode, " +
                "PRODUCT_CONDITION = :productCondition, " +
                "MANAGEMENT_TYPE = :managementType, " +
                "OPTION_TYPE = :optionType, " +
                "REGULAR_PRICE = :regularPrice, " +
                "CURRENT_PRICE = :currentPrice, " +
                "DISCOUNT_RATE = :discountRate, " +
                "SOLD_OUT = :soldOut, " +
                "DISPLAYED = :displayed, " +
                "PRODUCT_STATUS = :productStatus, " +
                "KEYWORDS = :keywords, " +
				"UPDATED_AT = :updatedAt " +
				"WHERE ID = :productGroupId";

        List<Map<String, Object>> batchValues = productGroups.stream()
                .map(group -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", group.getId())
                            .addValue("categoryId", group.getCategoryId())
                            .addValue("brandId", group.getBrandId())
							.addValue("sellerId", group.getSellerId())
							.addValue("productGroupName", group.getProductGroupName())
                            .addValue("styleCode", group.getStyleCode())
                            .addValue("productCondition", group.getProductCondition().toString())
                            .addValue("managementType", group.getManagementType().toString())
                            .addValue("optionType", group.getOptionType().toString())
                            .addValue("regularPrice", group.getRegularPrice())
                            .addValue("currentPrice", group.getCurrentPrice())
                            .addValue("discountRate", group.getDiscountRate())
                            .addValue("soldOut", group.isSoldOut())
                            .addValue("displayed", group.isDisplayed())
                            .addValue("productStatus", group.getProductStatus().toString())
                            .addValue("keywords", group.getKeywords())
							.addValue("updatedAt", LocalDateTime.now());
					return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

	private List<Long> getInsertedIds(int numberOfRows) {
		long firstInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
		return LongStream.range(firstInsertedId, firstInsertedId + numberOfRows)
			.boxed()
			.toList();
	}

}
