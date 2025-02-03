package com.ryuqq.core.alert;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

@Component
public class SlackNotifier {

	private static final Logger log = LoggerFactory.getLogger(SlackNotifier.class);

	private SlackNotifier() {
	}

	public static void sendMessage(String token, String channel, String message) {
		MethodsClient methodsClient = Slack.getInstance().methods(token);

		ChatPostMessageRequest request = ChatPostMessageRequest.builder()
			.token(token)
			.channel(channel)
			.text(message)
			.mrkdwn(true)
			.build();

		try {
			ChatPostMessageResponse chatPostMessageResponse = methodsClient.chatPostMessage(request);
			if(!chatPostMessageResponse.isOk()) {
				log.error("Failed to send Slack alert to channel '{}'.", chatPostMessageResponse.getError());
				throw new RuntimeException(chatPostMessageResponse.getError());
			}


		} catch (SlackApiException | IOException e) {
			log.error("Failed to send Slack alert to channel '{}'.", channel, e);
			throw new RuntimeException("Slack alert failed", e);
		}
	}


}
