package com.ryuqq.core.monitoring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;

@Configuration
public class MetricsConfig {

	@Bean
	public MeterRegistry meterRegistry() {
		return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	}

}
