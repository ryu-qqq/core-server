package com.ryuqq.devbase.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {
        "com.ryuqq.devbase.domain",
        "com.ryuqq.devbase.api",
        "com.ryuqq.devbase.batch",
        "com.ryuqq.devbase.storage",
		"com.ryuqq.devbase.logging",

})

public class DevBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevBaseApplication.class, args);
    }

}
