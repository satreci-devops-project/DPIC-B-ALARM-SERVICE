package dpicb.notification.controller;

import dpicb.notification.service.SlackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController("/notification")
public class NotificationController {

    private final SlackService slackService;

    @GetMapping("/github-push")
    public void githubPush(@RequestBody String message) {
        log.info(message);
    }
}
