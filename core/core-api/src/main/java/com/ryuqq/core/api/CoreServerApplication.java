package com.ryuqq.core.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {
		"com.ryuqq.core.api",
		"com.ryuqq.core.domain",
        "com.ryuqq.core.batch",
        "com.ryuqq.core.storage.db",
		"com.ryuqq.core.logging",
})

public class CoreServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreServerApplication.class, args);
    }

}
