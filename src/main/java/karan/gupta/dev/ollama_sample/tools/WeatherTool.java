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

    @Tool(description = "Get the weather for a given location")
    public WeatherResponse getWeather(WeatherRequest weatherRequest) {
        // Use UriComponentsBuilder for clean endpoint construction, with path from config
        log.info("Received lat lon: {}",  weatherRequest.lat() + " " + weatherRequest.lon());

        try{
            String endpoint = UriComponentsBuilder
                    .fromPath(configProperties.getUrl())
                    .queryParam("lat", weatherRequest.lat())
                    .queryParam("lon", weatherRequest.lon())
                    .queryParam("appid", configProperties.getApiKey())
                    .build()
                    .toUriString();
            WeatherResponse response = restClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .body(WeatherResponse.class);

            log.info("Weather API response: {}", response);
            return response;
        } catch (RuntimeException e) {
            throw new GlobalException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
}
