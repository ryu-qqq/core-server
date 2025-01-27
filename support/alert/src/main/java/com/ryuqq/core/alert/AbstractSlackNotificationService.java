package com.ryuqq.core.alert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractSlackNotificationService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractSlackNotificationService.class);

	private final String token;

	public AbstractSlackNotificationService(@Value("${slack.token}") String token) {
		this.token = token;
	}

	/**
	 * Slack 메시지를 전송하는 템플릿 메서드
	 * @param payload 메시지 페이로드
	 */
	public void notifySlack(T payload) {
		try {
			String channel = determineChannel();
			String formattedMessage = formatMessage(payload);
			SlackNotifier.sendMessage(token, channel, formattedMessage);
			log.info("Slack message sent to channel '{}' successfully.", channel);
		} catch (Exception e) {
			log.error("Failed to send Slack message: {}", e.getMessage(), e);
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
