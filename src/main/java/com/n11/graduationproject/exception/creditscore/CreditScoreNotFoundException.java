package com.n11.graduationproject.exception.creditscore;

import com.n11.graduationproject.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreditScoreNotFoundException extends BaseException {

    public CreditScoreNotFoundException() {
    }

    public CreditScoreNotFoundException(String message) {
        super(message);
    }

    public CreditScoreNotFoundException(List<String> messages) {
        super(messages);
    }
}
