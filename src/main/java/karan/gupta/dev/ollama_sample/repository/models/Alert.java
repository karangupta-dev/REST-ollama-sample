package karan.gupta.dev.ollama_sample.repository.models;

import java.util.List;

public record Alert(
    String sender_name,
    String event,
    long start,
    long end,
    String description,
    List<String> tags
) {}

