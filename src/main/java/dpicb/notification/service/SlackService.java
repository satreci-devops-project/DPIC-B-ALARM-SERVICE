package dpicb.notification.service;

import com.google.gson.JsonParseException;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class SlackService {

    @Value("${slack.token}")
    String token;

    public String githubResponseToMessage(String message) {
        StringBuffer generatedMessage = new StringBuffer();
        JSONParser jsonParser = new JSONParser();
        String repository;
        String pusher;
        Integer commitCount;
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(message) ;
            JSONObject jsonRepository = (JSONObject) jsonObject.get("repository");
            repository = (String) jsonRepository.get("name");
            JSONObject jsonPusher = (JSONObject) jsonObject.get("pusher");
            pusher = (String) jsonPusher.get("name");
            JSONArray commits = (JSONArray) jsonObject.get("commits");
            commitCount = commits.size();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        generatedMessage.append(pusher).append("님이 ")
                .append(repository).append("에")
                .append(commitCount).append("개의 commit을 push 했습니다.");
        return generatedMessage.toString();
    }

    public void postMessage(String message, String channel) {
        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(message)
                .build();
        try {
            ChatPostMessageResponse response = methods.chatPostMessage(request);
        } catch (SlackApiException | IOException e) {
            log.info(e.getMessage());
        }
    }
}
