package dpicb.notification.controller;

import dpicb.notification.service.SlackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final SlackService slackService;

    @PostMapping("/github-push")
    public void githubPush(@RequestBody String message) {
        String generatedMessage = slackService.githubResponseToMessage(message);
        slackService.postMessage(generatedMessage, "#dpic-b-alarm-test");
    }
}
