package com.ryuqq.core.alert;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "slack")
public class SlackConfig {


}
