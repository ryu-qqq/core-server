package com.ryuqq.core.storage.db.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class CoreDataSourceConfig {


	@Bean
	@ConfigurationProperties(prefix = "storage.datasource.core")
	public HikariConfig coreHikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public HikariDataSource dataSource(@Qualifier("coreHikariConfig") HikariConfig config) {
		return new HikariDataSource(config);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
