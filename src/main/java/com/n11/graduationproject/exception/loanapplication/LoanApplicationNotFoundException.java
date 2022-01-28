package com.n11.graduationproject.exception.loanapplication;

import com.n11.graduationproject.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoanApplicationNotFoundException extends BaseException {

    public LoanApplicationNotFoundException() {
    }

    public LoanApplicationNotFoundException(String message) {
        super(message);
    }

    public LoanApplicationNotFoundException(List<String> messages) {
        super(messages);
    }
}
