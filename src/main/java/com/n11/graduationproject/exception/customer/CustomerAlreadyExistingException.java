package com.n11.graduationproject.exception.customer;

import com.n11.graduationproject.exception.base.EntityAlreadyExistingException;

import java.util.List;

public class CustomerAlreadyExistingException extends EntityAlreadyExistingException {

    public CustomerAlreadyExistingException() {
    }

    public CustomerAlreadyExistingException(String... errorMessages) {
        super(errorMessages);
    }

    public CustomerAlreadyExistingException(List<String> errorMessages) {
        super(errorMessages);
    }
}