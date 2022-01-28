package com.n11.graduationproject.exception.loanapplication;

import com.n11.graduationproject.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanApplicationAlreadyExistingException extends BaseException {

    public LoanApplicationAlreadyExistingException() {
    }

    public LoanApplicationAlreadyExistingException(String message) {
        super(message);
    }

    public LoanApplicationAlreadyExistingException(List<String> messages) {
        super(messages);
    }
}
