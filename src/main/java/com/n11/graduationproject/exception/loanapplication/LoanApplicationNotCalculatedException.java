package com.n11.graduationproject.exception.loanapplication;

import com.n11.graduationproject.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class LoanApplicationNotCalculatedException extends BaseException {

    public LoanApplicationNotCalculatedException() {
    }

    public LoanApplicationNotCalculatedException(String message) {
        super(message);
    }

    public LoanApplicationNotCalculatedException(List<String> messages) {
        super(messages);
    }
}
