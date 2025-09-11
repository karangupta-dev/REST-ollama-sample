package karan.gupta.dev.ollama_sample.repository.models;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

public record ChatRequest(
        @NotEmpty @Size(min = 2, max = 1000, message = "Cannot be empty or more than 50 chars.") String query) {
}