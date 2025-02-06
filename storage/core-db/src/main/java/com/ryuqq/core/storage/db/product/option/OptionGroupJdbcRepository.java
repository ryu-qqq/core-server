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
public class OptionGroupJdbcRepository {

	private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OptionGroupJdbcRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

	public List<Long> batchInsertOptionGroupsAndGetIds(List<OptionGroupEntity> optionGroupEntities) {
		int[] insertCounts = batchInsertOptionGroups(optionGroupEntities);
		int numberOfInsertedRows = insertCounts.length;
		return getInsertedIds(numberOfInsertedRows);
	}

	public int[] batchInsertOptionGroups(List<OptionGroupEntity> optionGroupEntities) {
		String sql = "INSERT INTO OPTION_GROUP " +
			"(OPTION_NAME) " +
			"VALUES (:optionName, :optionValue)";

		List<Map<String, Object>> batchValues = optionGroupEntities.stream()
			.map(option -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("optionName", option.getOptionName());
				return params.getValues();
			})
			.toList();

		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

	public int[] batchUpdateOptionDetails(List<OptionGroupEntity> optionGroupEntities) {
		String sql = "UPDATE OPTION_GROUP " +
			"SET OPTION_NAME = :optionName, " +
			"UPDATED_AT = :updatedAt, " +
			"DELETED = :deleted " +
			"WHERE ID = :id";

		List<Map<String, Object>> batchValues = optionGroupEntities.stream()
			.map(option -> {
				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("optionName", option.getOptionName())
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
