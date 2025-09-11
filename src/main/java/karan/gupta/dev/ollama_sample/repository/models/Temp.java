package karan.gupta.dev.ollama_sample.repository.models;

public record Temp(
    double day,
    double min,
    double max,
    double night,
    double eve,
    double morn
) {}

