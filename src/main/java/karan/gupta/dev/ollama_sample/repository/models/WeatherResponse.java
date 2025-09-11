package karan.gupta.dev.ollama_sample.repository.models;

import java.util.List;

public record WeatherResponse(
    double lat,
    double lon,
    String timezone,
    int timezone_offset,
    Current current,
    List<Minutely> minutely,
    List<Hourly> hourly,
    List<Daily> daily,
    List<Alert> alerts
) {}
