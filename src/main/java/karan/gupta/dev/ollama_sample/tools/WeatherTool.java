package karan.gupta.dev.ollama_sample.tools;

import karan.gupta.dev.ollama_sample.controller.exception.GlobalException;
import karan.gupta.dev.ollama_sample.repository.models.WeatherRequest;
import karan.gupta.dev.ollama_sample.repository.models.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@Component
public class WeatherTool {
    private final WeatherConfigProperties configProperties;
    private final RestClient restClient;

    @Autowired
    public WeatherTool(WeatherConfigProperties configProperties) {
        this.configProperties = configProperties;
        this.restClient = RestClient.builder().build();
    }

    @Tool(description = "Get the weather for a given location, the tool requires city name. Its API key is configured in the application properties.")
    public String getWeather(String city) { // TODO: check how to map custom request objects
        // Use UriComponentsBuilder for clean endpoint construction, with path from config
        log.info("Received city name: {}",  city);

        try{
            String endpoint = UriComponentsBuilder
                    .fromUriString(configProperties.getUrl())
                    .queryParam("key", configProperties.getKey())
                    .queryParam("q", city)
                    .queryParam("aqi", "no")
                    .build()
                    .toUriString();
            String response = restClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .body(String.class); // TODO: check how to use custom json response

            log.info("Weather API response: {}", response);
            return response;
        } catch (RuntimeException e) {
            throw new GlobalException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
}
