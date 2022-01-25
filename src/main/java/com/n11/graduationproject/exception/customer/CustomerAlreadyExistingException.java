package com.n11.graduationproject.exception.customer;

import com.n11.graduationproject.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistingException extends BaseException {

    public CustomerAlreadyExistingException() {
    }

    public CustomerAlreadyExistingException(String message) {
        super(message);
    }

    public CustomerAlreadyExistingException(List<String> messages) {
        super(messages);
    }
}