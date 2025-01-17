package com.ryuqq.core.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ConfigurationPropertiesScan
@SpringBootTest
@ActiveProfiles("test")
class CoreServerApplicationTests {

    @Test
    void contextLoads() {
    }

}
