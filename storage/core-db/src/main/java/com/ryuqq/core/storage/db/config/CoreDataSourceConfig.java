package com.ryuqq.core.storage.db.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import com.ryuqq.core.storage.db.QueryListener;

@Configuration
public class CoreDataSourceConfig {

	@Bean
	@ConfigurationProperties(prefix = "storage.datasource.core")
	public HikariConfig coreHikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public HikariDataSource coreDataSource(@Qualifier("coreHikariConfig") HikariConfig config) {
		return new HikariDataSource(config);
	}

	@Bean
	public DataSource dataSource(DataSource dataSource) {
		SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
		loggingListener.setQueryLogEntryCreator(new DefaultQueryLogEntryCreator());
		return ProxyDataSourceBuilder.create(dataSource)
			.name("PROXY-DS")
			.listener(loggingListener)
			.listener(new QueryListener(200, 500))
			.build();
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
