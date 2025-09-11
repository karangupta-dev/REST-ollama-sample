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
                .defaultSystem("""
                        You are a helpful assistant.
                  
                        IMPORTANT RULES:
                        1. For ALL questions that are NOT about weather - answer using your training knowledge only. Do NOT use any tools.
                        
                        2. ONLY use the weather tool when:
                        - User specifically asks about current weather
                        - User asks "What's the weather like in [city]?"
                        - User asks about temperature, rain, or weather conditions for a specific location
                        
                        3. When using the weather tool:
                        - Extract the city name from the user's question
                        - Use the weather tool to get current information
                        - The tool will return JSON data
                        
                        4. If information is not available, explain why.
                        
                        Examples:
                        - "What's the capital of France?" → Answer with your knowledge (Paris)
                        - "Tell me about history" → Answer with your knowledge
                        - "What's the weather in London?" → Use the weather tool
                        - "Is it raining in Tokyo?" → Use the weather tool
                    """)
                .build();

    }
}