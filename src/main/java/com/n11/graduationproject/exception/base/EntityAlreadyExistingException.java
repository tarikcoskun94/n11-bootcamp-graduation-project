package com.n11.graduationproject.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityAlreadyExistingException extends RuntimeException {

    private String errorTitle = "Same fields were existing!";
    private List<String> errorMessages;

    public EntityAlreadyExistingException() {
    }

    public EntityAlreadyExistingException(String... errorMessages) {
        this.errorMessages = Arrays.asList(errorMessages);
    }

    public EntityAlreadyExistingException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}