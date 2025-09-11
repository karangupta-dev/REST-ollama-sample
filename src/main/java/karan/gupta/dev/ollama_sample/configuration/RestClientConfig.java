package karan.gupta.dev.ollama_sample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import karan.gupta.dev.ollama_sample.tools.WeatherConfigProperties;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient restClient(WeatherConfigProperties configProperties) {
        return RestClient.builder().baseUrl(configProperties.getUrl()).build();
    }
}

