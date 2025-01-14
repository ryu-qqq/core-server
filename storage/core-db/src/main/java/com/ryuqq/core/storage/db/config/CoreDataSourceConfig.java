package com.ryuqq.core.storage.db.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import com.ryuqq.core.storage.db.SlowQueryListener;

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
			.listener(new SlowQueryListener(500))
			.build();
	}

}
