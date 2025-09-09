package karan.gupta.dev.ollama_sample.controller.exception;

import org.springframework.http.HttpStatus;

// A class to get more detail back to the client (by using the HTTP response body)
public record DetailedErrorResponse(String apiPath, HttpStatus errorCode, String errorMessage) { }
