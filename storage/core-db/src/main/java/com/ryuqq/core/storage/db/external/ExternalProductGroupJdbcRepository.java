package com.ryuqq.core.storage.db.external;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ExternalProductGroupJdbcRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ExternalProductGroupJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public int[] batchInsertExternalProductGroups(List<ExternalProductGroupEntity> externalProductGroupEntities) {
		String sql = "INSERT INTO EXTERNAL_PRODUCT_GROUP " +
			"(SITE_ID, PRODUCT_GROUP_ID, EXTERNAL_PRODUCT_GROUP_ID, BRAND_ID, CATEGORY_ID, PRODUCT_NAME, " +
			"REGULAR_PRICE, CURRENT_PRICE, STATUS, FIXED_PRICE, SOLD_OUT, DISPLAYED) " +
			"VALUES (:siteId, :productGroupId, :externalProductGroupId, :brandId, :categoryId, :productName, " +
			":regularPrice, :currentPrice, :status, :fixedPrice, :soldOut, :displayed)";

		List<Map<String, Object>> batchValues = externalProductGroupEntities.stream()
			.map(group -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("siteId", group.getSiteId())
					.addValue("productGroupId", group.getProductGroupId())
					.addValue("externalProductGroupId", group.getExternalProductGroupId())
					.addValue("brandId", group.getBrandId())
					.addValue("categoryId", group.getCategoryId())
					.addValue("productName", group.getProductName())
					.addValue("regularPrice", group.getRegularPrice())
					.addValue("currentPrice", group.getCurrentPrice())
					.addValue("status", group.getStatus().name())
					.addValue("fixedPrice", group.isFixedPrice())
					.addValue("soldOut", group.isSoldOut())
					.addValue("displayed", group.isDisplayed());
				return params.getValues();
			})
			.toList();

		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

	public int[] batchUpdateProductGroups(List<ExternalProductGroupEntity> externalProductGroupEntities) {
		String sql = "UPDATE EXTERNAL_PRODUCT_GROUP SET " +
			"EXTERNAL_PRODUCT_GROUP_ID = :externalProductGroupId, " +
			"BRAND_ID = :brandId, " +
			"CATEGORY_ID = :categoryId, " +
			"PRODUCT_NAME = :productName, " +
			"REGULAR_PRICE = :regularPrice, " +
			"CURRENT_PRICE = :currentPrice, " +
			"STATUS = :status, " +
			"FIXED_PRICE = :fixedPrice, " +
			"SOLD_OUT = :soldOut, " +
			"DISPLAYED = :displayed, " +
			"UPDATED_AT = :updatedAt " +
			"WHERE PRODUCT_GROUP_ID = :productGroupId AND SITE_ID = :siteId ";

		List<Map<String, Object>> batchValues = externalProductGroupEntities.stream()
			.map(group -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("externalProductGroupId", group.getExternalProductGroupId())
					.addValue("siteId", group.getSiteId())
					.addValue("productGroupId", group.getProductGroupId())
					.addValue("brandId", group.getBrandId())
					.addValue("categoryId", group.getCategoryId())
					.addValue("productName", group.getProductName())
					.addValue("regularPrice", group.getRegularPrice())
					.addValue("currentPrice", group.getCurrentPrice())
					.addValue("status", group.getStatus().name())
					.addValue("fixedPrice", group.isFixedPrice())
					.addValue("soldOut", group.isSoldOut())
					.addValue("displayed", group.isDisplayed())
					.addValue("updatedAt", LocalDateTime.now());

				return params.getValues();
			})
			.toList();

		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

}
