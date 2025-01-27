package com.ryuqq.core.storage.db.product.notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductNoticeJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductNoticeJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertProductNotices(List<ProductNoticeEntity> productNotices) {
        String sql = "INSERT INTO PRODUCT_NOTICE " +
                "(PRODUCT_GROUP_ID, MATERIAL, COLOR, SIZE, MAKER, ORIGIN, WASHING_METHOD, " +
                "YEAR_MONTH_DAY, ASSURANCE_STANDARD, AS_PHONE) " +
                "VALUES (:productGroupId, :material, :color, :size, :maker, :origin, :washingMethod, " +
                ":yearMonth, :assuranceStandard, :asPhone)";

        List<Map<String, Object>> batchValues = productNotices.stream()
                .map(productNotice -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", productNotice.getProductGroupId())
                            .addValue("material", productNotice.getMaterial())
                            .addValue("color", productNotice.getColor())
                            .addValue("size", productNotice.getSize())
                            .addValue("maker", productNotice.getMaker())
                            .addValue("origin", productNotice.getOrigin().toString()) // Enum을 String으로 변환
                            .addValue("washingMethod", productNotice.getWashingMethod())
                            .addValue("yearMonth", productNotice.getYearMonth())
                            .addValue("assuranceStandard", productNotice.getAssuranceStandard())
                            .addValue("asPhone", productNotice.getAsPhone());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }


    public int[] batchUpdateProductNotices(List<ProductNoticeEntity> productNotices) {
        String sql = "UPDATE PRODUCT_NOTICE " +
                "SET MATERIAL = :material, " +
                "COLOR = :color, " +
                "SIZE = :size, " +
                "MAKER = :maker, " +
                "ORIGIN = :origin, " +
                "WASHING_METHOD = :washingMethod, " +
                "YEAR_MONTH_DAY = :yearMonth, " +
                "ASSURANCE_STANDARD = :assuranceStandard, " +
                "AS_PHONE = :asPhone, " +
				"UPDATED_AT = :updatedAt " +

			"WHERE PRODUCT_GROUP_ID = :productGroupId";

        List<Map<String, Object>> batchValues = productNotices.stream()
                .map(productNotice -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", productNotice.getProductGroupId())
                            .addValue("material", productNotice.getMaterial())
                            .addValue("color", productNotice.getColor())
                            .addValue("size", productNotice.getSize())
                            .addValue("maker", productNotice.getMaker())
                            .addValue("origin", productNotice.getOrigin().toString())
                            .addValue("washingMethod", productNotice.getWashingMethod())
                            .addValue("yearMonth", productNotice.getYearMonth())
                            .addValue("assuranceStandard", productNotice.getAssuranceStandard())
                            .addValue("asPhone", productNotice.getAsPhone())
							.addValue("updatedAt", LocalDateTime.now());

					return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

}
