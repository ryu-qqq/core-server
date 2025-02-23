package com.ryuqq.core.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;

@Configuration
public class MetricsConfig {

	private static final Logger logger = LoggerFactory.getLogger(MetricsConfig.class);

	@Bean
	public ApplicationRunner debugAutoBinders(MeterRegistry meterRegistry) {
		return args -> {
			logger.info("### Checking auto-registered JVM & System metrics ###");

			// 현재 등록된 메트릭 출력
			meterRegistry.getMeters().forEach(meter -> logger.info("Registered Metric: {}", meter.getId().getName()));

			// JVM 및 시스템 메트릭 수동 등록
			new JvmMemoryMetrics().bindTo(meterRegistry);
			new ProcessorMetrics().bindTo(meterRegistry);
			new UptimeMetrics().bindTo(meterRegistry);
			new FileDescriptorMetrics().bindTo(meterRegistry);

			// JvmGcMetrics는 try-with-resources 사용하여 안전하게 등록
			try (JvmGcMetrics jvmGcMetrics = new JvmGcMetrics()) {
				jvmGcMetrics.bindTo(meterRegistry);
			}

			logger.info("✅ JVM & System Metrics manually registered!");
		};
	}
}
