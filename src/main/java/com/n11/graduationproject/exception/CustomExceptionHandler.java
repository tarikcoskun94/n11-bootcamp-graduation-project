package com.n11.graduationproject.exception;

import com.n11.graduationproject.dto.ErrorMessage;
import com.n11.graduationproject.dto.exception.ExceptionDTO;
import com.n11.graduationproject.exception.creditscore.CreditScoreNotFoundException;
import com.n11.graduationproject.exception.customer.CustomerAlreadyExistingException;
import com.n11.graduationproject.exception.customer.CustomerNotFoundException;
import com.n11.graduationproject.exception.customer.LoanCustomerAlreadyExistingException;
import com.n11.graduationproject.exception.customer.LoanCustomerNotFoundException;
import com.n11.graduationproject.exception.loanapplication.LoanApplicationAlreadyExistingException;
import com.n11.graduationproject.exception.loanapplication.LoanApplicationNotCalculatedException;
import com.n11.graduationproject.exception.loanapplication.LoanApplicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * START: HttpStatus.NOT_FOUND Exceptions
     */
    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setTimeStamp(LocalDateTime.now());
        errorMessage.setMessages(customerNotFoundException.getMessages());

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setExceptionPath(customerNotFoundException.toString());
        errorMessage.addContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(value = {CreditScoreNotFoundException.class})
    public ResponseEntity<?> handleCreditScoreNotFoundException(CreditScoreNotFoundException creditScoreNotFoundException) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setTimeStamp(LocalDateTime.now());
        errorMessage.setMessages(creditScoreNotFoundException.getMessages());

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setExceptionPath(creditScoreNotFoundException.toString());
        errorMessage.addContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(value = {LoanCustomerNotFoundException.class})
    public ResponseEntity<?> handleLoanCustomerNotFoundException(LoanCustomerNotFoundException loanCustomerNotFoundException) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setTimeStamp(LocalDateTime.now());
        errorMessage.setMessages(loanCustomerNotFoundException.getMessages());

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setExceptionPath(loanCustomerNotFoundException.toString());
        errorMessage.addContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(value = {LoanApplicationNotFoundException.class})
    public ResponseEntity<?> handleLoanApplicationNotFoundException(LoanApplicationNotFoundException loanApplicationNotFoundException) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setTimeStamp(LocalDateTime.now());
        errorMessage.setMessages(loanApplicationNotFoundException.getMessages());

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setExceptionPath(loanApplicationNotFoundException.toString());
        errorMessage.addContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }
    /** END: HttpStatus.NOT_FOUND Exceptions */

    /**
     * START: HttpStatus.BAD_REQUEST Exceptions
     */
    @ExceptionHandler(value = {CustomerAlreadyExistingException.class})
    public ResponseEntity<?> handleCustomerAlreadyExistingException(CustomerAlreadyExistingException customerAlreadyExistingException) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setTimeStamp(LocalDateTime.now());
        errorMessage.setMessages(customerAlreadyExistingException.getMessages());

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setExceptionPath(customerAlreadyExistingException.toString());
        errorMessage.addContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(value = {LoanCustomerAlreadyExistingException.class})
    public ResponseEntity<?> handleLoanCustomerAlreadyExistingException(LoanCustomerAlreadyExistingException loanCustomerAlreadyExistingException) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setTimeStamp(LocalDateTime.now());
        errorMessage.setMessages(loanCustomerAlreadyExistingException.getMessages());

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setExceptionPath(loanCustomerAlreadyExistingException.toString());
        errorMessage.addContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(value = {LoanApplicationAlreadyExistingException.class})
    public ResponseEntity<?> handleLoanApplicationAlreadyExistingException(LoanApplicationAlreadyExistingException loanApplicationAlreadyExistingException) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setTimeStamp(LocalDateTime.now());
        errorMessage.setMessages(loanApplicationAlreadyExistingException.getMessages());

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setExceptionPath(loanApplicationAlreadyExistingException.toString());
        errorMessage.addContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }
    /** END: HttpStatus.BAD_REQUEST Exceptions */

    /**
     * START: HttpStatus.BAD_GATEWAY Exceptions
     */
    @ExceptionHandler(value = {LoanApplicationNotCalculatedException.class})
    public ResponseEntity<?> handleLoanApplicationNotCalculatedException(LoanApplicationNotCalculatedException loanApplicationNotCalculatedException) {

        HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setTimeStamp(LocalDateTime.now());
        errorMessage.setMessages(loanApplicationNotCalculatedException.getMessages());

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setExceptionPath(loanApplicationNotCalculatedException.toString());
        errorMessage.addContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }
    /** END: HttpStatus.BAD_GATEWAY Exceptions */
}