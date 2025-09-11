package karan.gupta.dev.ollama_sample.controller;

import karan.gupta.dev.ollama_sample.controller.exception.GlobalException;
import karan.gupta.dev.ollama_sample.repository.models.ChatRequest;
import karan.gupta.dev.ollama_sample.tools.WeatherTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.net.ConnectException;

@RestController
@RequestMapping(path = "/ollama-chat")
@Slf4j
public class ChatController {

    private final ChatClient chatClient;
    private final WeatherTool weatherTool;

    public ChatController(ChatClient chatClient, WeatherTool weatherTool) {
        this.chatClient = chatClient;
        this.weatherTool = weatherTool;
    }

    @PostMapping
    public String getInfo(@Valid @RequestBody ChatRequest chatRequest) {
        log.info("Received chat request: {}", chatRequest);

        try {
            String response = chatClient.prompt()
                    .user(chatRequest.query())
//                    .tools(weatherTool)
                    .call()
                    .content();

            log.info("Ollama response received, length: {}", response.length());

            return response;
        }catch (ResourceAccessException e) {
            log.error("Resource access error during chat processing", e);

            if (isConnectionError(e)) {             // Check if it's a connection issue
                throw new GlobalException("Unable to connect to Ollama server. Please ensure the server is running and accessible.", HttpStatus.SERVICE_UNAVAILABLE);
            } else {
                throw new GlobalException("Network error occurred while communicating with Ollama server.", HttpStatus.BAD_GATEWAY);
            }

        }
        catch (Exception e) {
            throw new GlobalException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isConnectionError(ResourceAccessException e) {
        Throwable cause = e.getCause();
        while (cause != null) {
            if (cause instanceof ConnectException ||
                    cause instanceof java.net.SocketTimeoutException ||
                    cause instanceof java.net.UnknownHostException ||
                    cause.getMessage() != null && cause.getMessage().contains("Connection refused")) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }
}
