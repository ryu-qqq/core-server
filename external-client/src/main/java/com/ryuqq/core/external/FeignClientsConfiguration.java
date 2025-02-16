package com.ryuqq.core.external;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.ryuqq.core.external")
@Configuration
public class FeignClientsConfiguration {

}
