package com.n11.graduationproject.exception.customer;

import com.n11.graduationproject.exception.base.EntityNotFoundException;

import java.util.List;

public class CustomerNotFoundException extends EntityNotFoundException {

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String... errorMessages) {
        super(errorMessages);
    }

    public CustomerNotFoundException(List<String> errorMessages) {
        super(errorMessages);
    }
}