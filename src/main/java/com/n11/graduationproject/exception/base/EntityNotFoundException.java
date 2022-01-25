package com.n11.graduationproject.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private String errorTitle = "The entity is not found!";
    private List<String> errorMessages;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String... errorMessages) {
        this.errorMessages = Arrays.asList(errorMessages);
    }

    public EntityNotFoundException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}