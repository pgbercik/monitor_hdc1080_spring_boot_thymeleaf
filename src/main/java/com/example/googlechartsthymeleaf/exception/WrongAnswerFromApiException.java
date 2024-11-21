package com.example.googlechartsthymeleaf.exception;

public class WrongAnswerFromApiException extends RuntimeException {
    public WrongAnswerFromApiException(String message) {
        super(message);
    }
}
