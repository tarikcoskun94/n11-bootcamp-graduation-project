package com.n11.graduationproject.exception.customer;

import com.n11.graduationproject.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanCustomerAlreadyExistingException extends BaseException {

    public LoanCustomerAlreadyExistingException() {
    }

    public LoanCustomerAlreadyExistingException(String message) {
        super(message);
    }

    public LoanCustomerAlreadyExistingException(List<String> messages) {
        super(messages);
    }
}
