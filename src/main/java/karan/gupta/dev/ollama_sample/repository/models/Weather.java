package karan.gupta.dev.ollama_sample.repository.models;

public record Weather(
    int id,
    String main,
    String description,
    String icon
) {}

