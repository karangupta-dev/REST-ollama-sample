package karan.gupta.dev.ollama_sample.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    public ChatClient chatClient(OllamaChatModel ollamaChatModel) {
        // Configure your ChatClient here
        // This depends on which AI library you're using

        return ChatClient.builder(ollamaChatModel)
                .defaultSystem("You are a helpful assistant")
                .build();

    }
}