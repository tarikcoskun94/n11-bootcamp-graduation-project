package com.n11.graduationproject.exception;

import com.n11.graduationproject.dto.ErrorMessage;
import com.n11.graduationproject.dto.exception.ExceptionDTO;
import com.n11.graduationproject.exception.customer.CustomerAlreadyExistingException;
import com.n11.graduationproject.exception.customer.CustomerNotFoundException;
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
        errorMessage.setContent(exceptionDTO);

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
        errorMessage.setContent(exceptionDTO);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }
    /** END: HttpStatus.BAD_REQUEST Exceptions */
}