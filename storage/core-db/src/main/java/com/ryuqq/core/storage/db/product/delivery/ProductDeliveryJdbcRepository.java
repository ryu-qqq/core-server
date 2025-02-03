package com.ryuqq.core.storage.db.product.delivery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDeliveryJdbcRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ProductDeliveryJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public int[] batchInsertProductDeliveries(List<ProductDeliveryEntity> productDeliveries) {
		String sql = "INSERT INTO PRODUCT_DELIVERY " +
			"(PRODUCT_GROUP_ID, DELIVERY_AREA, DELIVERY_FEE, DELIVERY_PERIOD_AVERAGE, " +
			"RETURN_METHOD_DOMESTIC, RETURN_COURIER_DOMESTIC, RETURN_CHARGE_DOMESTIC, " +
			"RETURN_EXCHANGE_AREA_DOMESTIC) " +
			"VALUES (:productGroupId, :deliveryArea, :deliveryFee, :deliveryPeriodAverage, " +
			":returnMethodDomestic, :returnCourierDomestic, :returnChargeDomestic, " +
			":returnExchangeAreaDomestic)";

		List<Map<String, Object>> batchValues = productDeliveries.stream()
			.map(delivery -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("productGroupId", delivery.getProductGroupId())
					.addValue("deliveryArea", delivery.getDeliveryArea())
					.addValue("deliveryFee", delivery.getDeliveryFee())
					.addValue("deliveryPeriodAverage", delivery.getDeliveryPeriodAverage())
					.addValue("returnMethodDomestic", delivery.getReturnMethodDomestic().toString())
					.addValue("returnCourierDomestic", delivery.getReturnCourierDomestic().toString())
					.addValue("returnChargeDomestic", delivery.getReturnChargeDomestic())
					.addValue("returnExchangeAreaDomestic", delivery.getReturnExchangeAreaDomestic());
				return params.getValues();
			})
			.toList();

		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

	public int[] batchUpdateProductDeliveries(List<ProductDeliveryEntity> productDeliveries) {
		String sql = "UPDATE PRODUCT_DELIVERY " +
			"SET DELIVERY_AREA = :deliveryArea, " +
			"DELIVERY_FEE = :deliveryFee, " +
			"DELIVERY_PERIOD_AVERAGE = :deliveryPeriodAverage, " +
			"RETURN_METHOD_DOMESTIC = :returnMethodDomestic, " +
			"RETURN_COURIER_DOMESTIC = :returnCourierDomestic, " +
			"RETURN_CHARGE_DOMESTIC = :returnChargeDomestic, " +
			"RETURN_EXCHANGE_AREA_DOMESTIC = :returnExchangeAreaDomestic, " +
			"UPDATED_AT = :updatedAt " +
			"WHERE PRODUCT_GROUP_ID = :productGroupId";

		List<Map<String, Object>> batchValues = productDeliveries.stream()
			.map(delivery -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("productGroupId", delivery.getProductGroupId())
					.addValue("deliveryArea", delivery.getDeliveryArea())
					.addValue("deliveryFee", delivery.getDeliveryFee())
					.addValue("deliveryPeriodAverage", delivery.getDeliveryPeriodAverage())
					.addValue("returnMethodDomestic", delivery.getReturnMethodDomestic().toString())
					.addValue("returnCourierDomestic", delivery.getReturnCourierDomestic().toString())
					.addValue("returnChargeDomestic", delivery.getReturnChargeDomestic())
					.addValue("returnExchangeAreaDomestic", delivery.getReturnExchangeAreaDomestic())
					.addValue("updatedAt", LocalDateTime.now());

				return params.getValues();
			})
			.toList();

		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

}
