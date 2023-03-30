package dpicb.notification.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class SlackService {
    @Value(value = "${slack.token}")
    String token;

    public void postMessage(String message, String channel) {
        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods("");
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(message)
                .build();
        try {
            ChatPostMessageResponse response = methods.chatPostMessage(request);
            log.info("response : {}", response.getMessage());
        } catch (SlackApiException | IOException e) {
            log.info(e.getMessage());
        }
    }
}
