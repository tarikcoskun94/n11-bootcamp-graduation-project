package com.n11.graduationproject.exception.customer;

import com.n11.graduationproject.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends BaseException {

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(List<String> messages) {
        super(messages);
    }

}