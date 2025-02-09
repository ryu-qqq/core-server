package com.ryuqq.core.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {
		"com.ryuqq.core",
		"com.ryuqq.core.domain",
		"com.ryuqq.core.external",
		"com.ryuqq.core.monitoring",
		"com.monikit.starter"
})
public class CoreServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreServerApplication.class, args);
    }

}
