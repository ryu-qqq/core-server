package com.ryuqq.core.alert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ryuqq.core.api.NotificationService;


public abstract class AbstractSlackNotificationService<T> implements NotificationService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractSlackNotificationService.class);

	private final String token;

	public AbstractSlackNotificationService(@Value("${slack.token}") String token) {
		this.token = token;
	}


	public void notifySlack(T payload) {
		try {
			String channel = determineChannel();
			// String formattedMessage = formatMessage(payload);
			// SlackNotifier.sendMessage(token, channel, formattedMessage);
		} catch (Exception e) {
			handleNotificationError(payload, e);
		}
	}


	protected void handleNotificationError(T payload, Exception e) {
		log.warn("Notification failed for payload: {}", payload, e);
	}


	protected String determineChannel() {
		return SlackConstants.ERROR_CHANNEL;
	}

	protected abstract String formatMessage(T payload);
}
