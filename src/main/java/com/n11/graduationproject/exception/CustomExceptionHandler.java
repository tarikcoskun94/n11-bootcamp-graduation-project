package com.n11.graduationproject.exception;

import com.n11.graduationproject.dto.ExceptionDTO;
import com.n11.graduationproject.exception.base.BasicMessageResponseException;
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
    public ResponseEntity<?> handleUserNotFoundException(CustomerNotFoundException customerNotFoundException) {

        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .errorTitle(customerNotFoundException.getErrorTitle())
                .errorMessages(customerNotFoundException.getErrorMessages())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BasicMessageResponseException.class})
    public ResponseEntity<?> handleRequestException(BasicMessageResponseException basicMessageResponseException) {

        String responseMessage = basicMessageResponseException.getMessage();

        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }
    /** END: HttpStatus.NOT_FOUND Exceptions */

    /**
     * START: HttpStatus.BAD_REQUEST Exceptions
     */
    @ExceptionHandler(value = {CustomerAlreadyExistingException.class})
    public ResponseEntity<?> handleUserExistingFieldException(CustomerAlreadyExistingException userExistingFieldException) {

        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .errorTitle(userExistingFieldException.getErrorTitle())
                .errorMessages(userExistingFieldException.getErrorMessages())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    /** END: HttpStatus.BAD_REQUEST Exceptions */
}