package com.ryuqq.core.storage.db.product.image;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductGroupImageJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductGroupImageJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertProductGroupImages(List<ProductGroupImageEntity> productGroupImageEntities) {
        String sql = "INSERT INTO PRODUCT_GROUP_IMAGE " +
                "(PRODUCT_GROUP_ID, PRODUCT_GROUP_IMAGE_TYPE, IMAGE_URL, ORIGIN_URL) " +
                "VALUES (:productGroupId, :productGroupImageType, :imageUrl, :originUrl)";

        List<Map<String, Object>> batchValues = productGroupImageEntities.stream()
                .map(image -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", image.getProductGroupId())
                            .addValue("productGroupImageType", image.getProductImageType().toString())
                            .addValue("imageUrl", image.getImageUrl())
                            .addValue("originUrl", image.getOriginUrl());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public int[] batchUpdateProductGroups(List<ProductGroupImageEntity> productGroupImageEntities) {
        String sql = "UPDATE PRODUCT_GROUP_IMAGE " +
                "SET IMAGE_URL = :imageUrl, " +
                "ORIGIN_URL = :originUrl, " +
                "PRODUCT_GROUP_IMAGE_TYPE = :productGroupImageType, " +
                "DELETED = :deleteYn, " +
				"UPDATED_AT = :updatedAt " +
				"WHERE ID = :id";

        List<Map<String, Object>> batchValues = productGroupImageEntities.stream()
                .map(image -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("imageUrl", image.getImageUrl())
                            .addValue("originUrl", image.getOriginUrl())
                            .addValue("productGroupImageType", image.getProductImageType().name())
                            .addValue("deleted", image.isDeleted())
							.addValue("updatedAt", LocalDateTime.now())
							.addValue("id", image.getId());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

}
