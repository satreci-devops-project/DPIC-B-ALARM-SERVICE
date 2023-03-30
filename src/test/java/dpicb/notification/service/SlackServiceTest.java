package dpicb.notification.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlackServiceTest {

    SlackService slackService = new SlackService();

    @Test
    void postMessageTest() {
        // given
        String message = "Hello world";
        String channel = "#dpic-b-alarm-test";

        // when
        slackService.postMessage(message, channel);

        // then
    }
}