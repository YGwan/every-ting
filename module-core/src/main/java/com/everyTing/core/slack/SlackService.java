package com.everyTing.core.slack;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackService {

    @Value("${slack.webhook.url}")
    private String webhookUrl;
    private final Slack slackClient;

    public SlackService() {
        this.slackClient = Slack.getInstance();
    }

    public void send(String message) {
        final Payload payload = Payload.builder().text(message).build();
        try {
            slackClient.send(webhookUrl, payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
