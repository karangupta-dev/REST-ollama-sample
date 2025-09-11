package karan.gupta.dev.ollama_sample.repository.models;

import java.util.List;

public record Current(
    long dt,
    long sunrise,
    long sunset,
    double temp,
    double feels_like,
    int pressure,
    int humidity,
    double dew_point,
    double uvi,
    int clouds,
    int visibility,
    double wind_speed,
    int wind_deg,
    double wind_gust,
    List<Weather> weather
) {}

