package karan.gupta.dev.ollama_sample.tools;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weather.api")
public class WeatherConfigProperties {

    @NotBlank(message = "Weather API key is required")
    private String key;

    @NotBlank(message = "Weather API endpoint is required")
    private String url;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

