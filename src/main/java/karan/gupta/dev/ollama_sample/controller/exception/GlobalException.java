package karan.gupta.dev.ollama_sample.controller.exception;

public class GlobalException extends RuntimeException{
    public GlobalException(String message){
        super(message);
    }
}
