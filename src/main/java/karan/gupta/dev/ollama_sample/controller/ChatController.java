package karan.gupta.dev.ollama_sample.controller;

import karan.gupta.dev.ollama_sample.repository.models.ChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/ollama-chat")
@Slf4j
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping
    public String getTemperature(@Valid @RequestBody ChatRequest chatRequest) {
        log.info("Received chat request: {}", chatRequest);

        try {
            String response = chatClient.prompt()
                    .user(chatRequest.query())
                    .call()
                    .content();

            log.info("Ollama response received, length: {}", response.length());

            return response;
        } catch (Exception e) {
            log.error("Error during chat processing", e);
            throw e;
        }
    }
}
