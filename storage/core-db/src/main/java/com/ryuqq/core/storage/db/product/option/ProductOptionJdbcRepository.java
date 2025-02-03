package com.ryuqq.core.storage.db.product.option;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOptionJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductOptionJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertProductOptions(List<ProductOptionEntity> productOptionEntities) {
        String sql = "INSERT INTO PRODUCT_OPTION " +
                "(PRODUCT_ID, OPTION_GROUP_ID, OPTION_DETAIL_ID) " +
                "VALUES (:productId, :optionGroupId, :optionDetailId)";

        List<Map<String, Object>> batchValues = productOptionEntities.stream()
                .map(option -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productId", option.getProductId())
                            .addValue("optionGroupId", option.getOptionGroupId())
                            .addValue("optionDetailId", option.getOptionDetailId());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public int[] batchUpdateProductOptions(List<ProductOptionEntity> productOptionEntities) {
        String sql = "UPDATE PRODUCT_OPTION " +
                "SET OPTION_GROUP_ID = :optionGroupId, " +
                " OPTION_DETAIL_ID = :optionDetailId, " +
				"UPDATED_AT = :updatedAt, " +
				"DELETED = :deleteYn " +
                "WHERE PRODUCT_ID = :productId";

        List<Map<String, Object>> batchValues = productOptionEntities.stream()
                .map(option -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productId", option.getProductId())
                            .addValue("optionGroupId", option.getOptionGroupId())
                            .addValue("optionDetailId", option.getOptionDetailId())
							.addValue("updatedAt", LocalDateTime.now())
							.addValue("deleted", option.isDeleted());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public void softDeleteAll(List<Long> productIds) {
        String sql = "UPDATE PRODUCT_OPTION " +
                "SET DELETE_YN = :deleteYn, " +
				"UPDATED_AT = :updatedAt " +
				"WHERE PRODUCT_ID = :productId ";

        List<Map<String, Object>> batchValues = productIds.stream()
                .map(Long -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productId", Long)
							.addValue("updatedAt", LocalDateTime.now())
							.addValue("deleteYn", true);
                    return params.getValues();
                })
                .toList();

        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }



}
