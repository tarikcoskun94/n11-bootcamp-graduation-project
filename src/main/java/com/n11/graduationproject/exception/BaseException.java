package com.n11.graduationproject.exception;

import java.util.Arrays;
import java.util.List;

public abstract class BaseException extends RuntimeException {

    private List<String> messages;

    public BaseException() {
    }

    public BaseException(String... messages) {
        this.messages = Arrays.asList(messages);
    }

    public BaseException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}