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
public class OptionDetailJdbcRepository {
	private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OptionDetailJdbcRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

	public List<Long> batchInsertOptionDetailsAndGetIds(List<OptionDetailEntity> optionDetailEntities) {
		int[] insertCounts = batchInsertOptionDetails(optionDetailEntities);
		int numberOfInsertedRows = insertCounts.length;
		return getInsertedIds(numberOfInsertedRows);
	}

	public int[] batchInsertOptionDetails(List<OptionDetailEntity> optionDetailEntities) {
		String sql = "INSERT INTO OPTION_DETAIL " +
			"(OPTION_GROUP_ID, OPTION_VALUE) " +
			"VALUES (:optionGroupId, :optionValue)";

		List<Map<String, Object>> batchValues = optionDetailEntities.stream()
			.map(option -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("optionGroupId", option.getOptionGroupId())
					.addValue("optionValue", option.getOptionValue());
				return params.getValues();
			})
			.toList();

		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

	public int[] batchUpdateOptionDetails(List<OptionDetailEntity> optionDetailEntities) {
		String sql = "UPDATE OPTION_DETAIL " +
			"SET OPTION_GROUP_ID = :optionGroupId, " +
			" OPTION_VALUE = :optionValue, " +
			"UPDATED_AT = :updatedAt, " +
			"DELETED = :deleteYn " +
			"WHERE ID = :id";

		List<Map<String, Object>> batchValues = optionDetailEntities.stream()
			.map(option -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("optionGroupId", option.getOptionGroupId())
					.addValue("optionValue", option.getOptionValue())
					.addValue("updatedAt", LocalDateTime.now())
					.addValue("deleted", option.isDeleted())
					.addValue("id", option.getId());
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
