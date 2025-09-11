package karan.gupta.dev.ollama_sample.repository.models;

import java.util.List;

public record Daily(
    long dt,
    long sunrise,
    long sunset,
    long moonrise,
    long moonset,
    double moon_phase,
    String summary,
    Temp temp,
    FeelsLike feels_like,
    int pressure,
    int humidity,
    double dew_point,
    double wind_speed,
    int wind_deg,
    double wind_gust,
    List<Weather> weather,
    int clouds,
    double pop,
    Double rain,
    double uvi
) {}

