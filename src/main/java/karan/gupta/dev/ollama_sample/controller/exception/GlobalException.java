package karan.gupta.dev.ollama_sample.controller.exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException{
    private final HttpStatus status;

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus status() {
        return status;
    }
}
