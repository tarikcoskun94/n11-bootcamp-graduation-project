package com.n11.graduationproject.exception;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseException extends RuntimeException {

    private List<String> messages;

    public BaseException() {
        this.messages = new ArrayList<>();
    }

    public BaseException(String message) {
        this();
        this.messages.add(message);
    }

    public BaseException(List<String> messages) {
        this();
        this.messages.clear();
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}