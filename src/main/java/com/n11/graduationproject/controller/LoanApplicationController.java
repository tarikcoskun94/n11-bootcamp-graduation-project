package com.n11.graduationproject.controller;

import com.n11.graduationproject.dto.SuccessMessage;
import com.n11.graduationproject.dto.loanapplication.LoanApplicationQueryRequestDTO;
import com.n11.graduationproject.dto.loanapplication.LoanApplicationResultDTO;
import com.n11.graduationproject.dto.loanapplication.LoanApplicationSaveRequestDTO;
import com.n11.graduationproject.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/loan-applications")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<SuccessMessage> save(@Valid @RequestBody LoanApplicationSaveRequestDTO loanApplicationSaveRequestDTO) {

        LoanApplicationResultDTO loanApplicationResultDTO = loanApplicationService.save(loanApplicationSaveRequestDTO);

        HttpStatus httpStatus = HttpStatus.CREATED;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Loan application has been created.");
        successMessage.addContent(loanApplicationResultDTO);

        return new ResponseEntity<>(successMessage, httpStatus);
    }

    @PostMapping(value = "/loan-query")
    public ResponseEntity<SuccessMessage> queryLoanApplication(@Valid @RequestBody LoanApplicationQueryRequestDTO loanApplicationQueryRequestDTO) {

        LoanApplicationResultDTO loanApplicationResultDTO = loanApplicationService.queryLoanApplication(loanApplicationQueryRequestDTO);

        HttpStatus httpStatus = HttpStatus.CREATED;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Loan application that has most recent update date has been found.");
        successMessage.addContent(loanApplicationResultDTO);

        return new ResponseEntity<>(successMessage, httpStatus);
    }
}
