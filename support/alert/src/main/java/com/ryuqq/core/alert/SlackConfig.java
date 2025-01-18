package com.ryuqq.core.alert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "slack")
public class SlackConfig {

	@Value("${slack.token}")
	private String slackToken;

	public String getSlackToken() {
		return slackToken;
	}

}
