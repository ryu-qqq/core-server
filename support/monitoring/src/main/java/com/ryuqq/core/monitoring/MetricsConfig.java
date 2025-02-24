package com.ryuqq.core.monitoring;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MetricsConfig {

	@Bean
	public ApplicationRunner checkRegisteredMetrics(MeterRegistry meterRegistry) {
		return args -> {
			System.out.println("### Registered Meters ###");
			meterRegistry.getMeters().forEach(meter -> System.out.println(meter.getId().getName()));
		};
	}

}
